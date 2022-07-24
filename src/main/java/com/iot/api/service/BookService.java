package com.iot.api.service;

import com.iot.api.exception.EntityNotFoundException;
import com.iot.api.model.*;
import com.iot.api.model.common.SuccessResponse;
import com.iot.api.model.request.create.AuthorCreationRequest;
import com.iot.api.model.request.create.BookCreationRequest;
import com.iot.api.model.request.create.BookLendRequest;
import com.iot.api.model.request.create.MemberCreationRequest;
import com.iot.api.repository.AuthorRepository;
import com.iot.api.repository.BookRepository;
import com.iot.api.repository.LendRepository;
import com.iot.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final AuthorRepository authorRepository;
    private final MemberRepository memberRepository;
    private final LendRepository lendRepository;
    private final BookRepository bookRepository;


    public SuccessResponse createBook(BookCreationRequest book) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());

        Optional<Author> author = authorRepository.findById(book.getAuthorId());
        if (!author.isPresent()) {
            successResponse.setStatus(HttpStatus.NO_CONTENT.value());
            successResponse.setMessage("Author Not Found");
        }else{
            Book bookToCreate = new Book();
            BeanUtils.copyProperties(book, bookToCreate);
            bookToCreate.setAuthor(author.get());
            successResponse.setData(Collections.singletonList(bookRepository.save(bookToCreate)));
        }
        return successResponse;
    }

    public SuccessResponse getAllBooks() {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setData(bookRepository.findAll());
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());
        return successResponse;
    }

    public SuccessResponse getBookByIsbn(String isbn) {
        Optional<Book> book = bookRepository.findByIsbn(isbn);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());

        if (book.isPresent()) {
            successResponse.setData(Collections.singletonList(book));
        } else {
            successResponse.setMessage("\"Cant find any book under given ISBN\"");
        }
        return successResponse;
    }


    public SuccessResponse getBookById(String id) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            successResponse.setData(Collections.singletonList(book));
        } else {
            successResponse.setStatus(HttpStatus.NOT_EXTENDED.value());
            successResponse.setMessage("Cant find any book under given ID");
        }
        return successResponse;
    }
    public SuccessResponse getBookByAuthorId(String authorId) {

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setStatus(HttpStatus.OK.value());
        successResponse.setMessage(HttpStatus.OK.name());

        Optional<Author> author = authorRepository.findById(authorId);
        if (!author.isPresent()) {
            successResponse.setStatus(HttpStatus.NOT_EXTENDED.value());
            successResponse.setMessage("Author Not Found");
        }else{
           successResponse.setData(bookRepository.findAllBookByAuthorId(authorId));
        }

        return successResponse;
    }

    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    public Member createMember(MemberCreationRequest request) {
        Member member = new Member();
        BeanUtils.copyProperties(request, member);
        member.setStatus(MemberStatus.ACTIVE);
        return memberRepository.save(member);
    }

    public Member updateMember(String id, MemberCreationRequest request) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (!optionalMember.isPresent()) {
            throw new EntityNotFoundException("Member not present in the database");
        }
        Member member = optionalMember.get();
        member.setLastName(request.getLastName());
        member.setFirstName(request.getFirstName());
        return memberRepository.save(member);
    }

    public Author createAuthor(AuthorCreationRequest request) {
        Author author = new Author();
        BeanUtils.copyProperties(request, author);
        return authorRepository.save(author);
    }

    public List<String> lendABook(BookLendRequest request) {

        Optional<Member> memberForId = memberRepository.findById(request.getMemberId());
        if (!memberForId.isPresent()) {
            throw new EntityNotFoundException("Member not present in the database");
        }

        Member member = memberForId.get();
        if (member.getStatus() != MemberStatus.ACTIVE) {
            throw new RuntimeException("User is not active to proceed a lending.");
        }

        List<String> booksApprovedToBurrow = new ArrayList<>();

        request.getBookIds().forEach(bookId -> {

            Optional<Book> bookForId = bookRepository.findById(bookId);
            if (!bookForId.isPresent()) {
                throw new EntityNotFoundException("Cant find any book under given ID");
            }

            Optional<Lend> burrowedBook = lendRepository.findByBookAndStatus(bookForId.get(), LendStatus.BURROWED);
            if (!burrowedBook.isPresent()) {
                booksApprovedToBurrow.add(bookForId.get().getName());
                Lend lend = new Lend();
                lend.setMember(memberForId.get());
                lend.setBook(bookForId.get());
                lend.setStatus(LendStatus.BURROWED);
                lend.setStartOn(Instant.now());
                lend.setDueOn(Instant.now().plus(30, ChronoUnit.DAYS));
                lendRepository.save(lend);
            }

        });
        return booksApprovedToBurrow;
    }

    public Book updateBook(String bookId, BookCreationRequest request) {
        Optional<Author> author = authorRepository.findById(request.getAuthorId());
        if (!author.isPresent()) {
            throw new EntityNotFoundException("Author Not Found");
        }
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            throw new EntityNotFoundException("Book Not Found");
        }
        Book book = optionalBook.get();
        book.setIsbn(request.getIsbn());
        book.setName(request.getName());
        book.setAuthor(author.get());
        return bookRepository.save(book);
    }

    public List<Member> readMembers() {
        return memberRepository.findAll();
    }


}
