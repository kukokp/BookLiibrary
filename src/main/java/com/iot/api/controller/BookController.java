package com.iot.api.controller;

import com.iot.api.model.Book;
import com.iot.api.model.common.SuccessResponse;
import com.iot.api.model.request.create.BookCreationRequest;
import com.iot.api.model.request.create.BookLendRequest;
import com.iot.api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/library")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/create-book")
    @PreAuthorize("hasRole('ROLE_BOOK_ADD')")
    public SuccessResponse<Book> createBook(@RequestBody BookCreationRequest request) {
        return bookService.createBook(request);
    }


    @GetMapping("/get-book")
    @PreAuthorize("hasRole('ROLE_BOOK_READ')")
    public ResponseEntity<SuccessResponse<Book>> getAllBook(@RequestParam(required = false) String isbn) {
        if (isbn == null) {
            return ResponseEntity.ok(bookService.getAllBooks());
        }
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }

    @GetMapping("/get-book/{bookId}")
    @PreAuthorize("hasRole('ROLE_BOOK_READ')")
    public ResponseEntity<SuccessResponse<Book>> getBookById(@PathVariable String bookId) {
        return ResponseEntity.ok(bookService.getBookById(bookId));
    }

    @GetMapping("/get-book-byauthor/{authorId}")
    @PreAuthorize("hasRole('ROLE_BOOK_READ')")
    public ResponseEntity<SuccessResponse<Book>> getBookByAuthorId(@PathVariable String authorId) {
        return ResponseEntity.ok(bookService.getBookByAuthorId(authorId));
    }

    @DeleteMapping("/book/{bookId}")
    @PreAuthorize("hasRole('ROLE_BOOK_DELETE')")
    public ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/book/{bookId}")
    @PreAuthorize("hasRole('ROLE_BOOK_UPDATE')")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") String bookId, @RequestBody BookCreationRequest request) {
        return ResponseEntity.ok(bookService.updateBook(bookId, request));
    }


    @PostMapping("/book/lend")
    @PreAuthorize("hasRole('ROLE_BOOK_ADD')")
    public ResponseEntity<List<String>> lendABook(@RequestBody BookLendRequest bookLendRequests) {
        return ResponseEntity.ok(bookService.lendABook(bookLendRequests));
    }
}
