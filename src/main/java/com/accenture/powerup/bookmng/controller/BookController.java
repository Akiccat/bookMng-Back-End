package com.accenture.powerup.bookmng.controller;

import com.accenture.powerup.bookmng.entity.BookEntity;
import com.accenture.powerup.bookmng.entity.ReturnBookEntity;
import com.accenture.powerup.bookmng.exception.BusinessFailureException;
import com.accenture.powerup.bookmng.requestdto.*;
import com.accenture.powerup.bookmng.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 搜索图书。
     * <p>根据信息搜索图书</p>
     *
     * @param form   表单。
     * @param errors 参数校验错误信息
     */
    @PostMapping(value = "/search")
    public List<BookEntity> searchBooks(@RequestBody @Valid SearchBookForm form, Errors errors) {
        if (errors.hasErrors()) {
            // 当form中存在验证错误，则抛出业务错误，将验证信息输出。
            throw new BusinessFailureException(errors);
        }
        return bookService.searchBooks(form.getBookId(),form.getAuthorName(), form.getPublisherName(), form.getBookName());
    }

    /**
     * 借阅图书。
     * <p>根据信息借阅图书</p>
     *
     * @param form   表单。
     * @param errors 参数校验错误信息
     */
    @PostMapping("/borrow")
    public int borrowBook(@RequestBody @Valid BorrowBookForm form, Errors errors){
        if (errors.hasErrors()) {
            // 当form中存在验证错误，则抛出业务错误，将验证信息输出。
            throw new BusinessFailureException(errors);
        }
        return bookService.borrowBook(form.getUserId(),form.getBookId(),form.getBorrowDate(),form.getReturnDate());
    }

    /**
     * 添加图书。
     * <p>根据信息添加图书</p>
     *
     * @param form   表单。
     * @param errors 参数校验错误信息
     */
    @PostMapping("/add")
    public int bookInformationInsert(@RequestBody @Valid AddBookForm form, Errors errors){
        if (errors.hasErrors()) {
            // 当form中存在验证错误，则抛出业务错误，将验证信息输出。
            throw new BusinessFailureException(errors);
        }
        return bookService.addBook(form.getBookName(),form.getPublisherName(),form.getAuthorName(),form.getQuantity());
    }

    /**
     * 修改图书信息。
     * <p>根据信息修改图书信息</p>
     *
     * @param form   表单。
     * @param errors 参数校验错误信息
     */
    @PostMapping("/edit")
    public int bookInformationUpdate(@RequestBody @Valid EditBookForm form, Errors errors){
        if (errors.hasErrors()) {
            // 当form中存在验证错误，则抛出业务错误，将验证信息输出。
            throw new BusinessFailureException(errors);
        }
        return bookService.editBook(form.getBookId(),form.getBookName(),form.getPublisherName(),form.getAuthorName(),form.getQuantity());
    }

    /**
     * 删除。
     * <p>根据ID将图书flag置为true</p>
     *
     * @param form   表单。
     * @param errors 参数校验错误信息
     */
    @PostMapping("/delete")
    public int bookInformationDelete(@RequestBody @Valid DeleteBookForm form, Errors errors){
        if (errors.hasErrors()) {
            // 当form中存在验证错误，则抛出业务错误，将验证信息输出。
            throw new BusinessFailureException(errors);
        }
        return bookService.deleteBook(form.getBookId());
    }

    /**
     * 应归还图书列表。
     * <p>根据输入信息查询用户借阅图书列表</p>
     *
     * @param form   表单。
     * @param errors 参数校验错误信息
     */
    @PostMapping("/getReturn")
    public List<ReturnBookEntity> getBookReturn(@RequestBody @Valid GetReturnBookForm form, Errors errors){
        if (errors.hasErrors()) {
            // 当form中存在验证错误，则抛出业务错误，将验证信息输出。
            throw new BusinessFailureException(errors);
        }
        return bookService.getReturnBooks(form.getBookId(),form.getUserId());
    }

    /**
     * 归还图书。
     * <p>根据输入信息,将指定的图书的delete_flag置为true</p>
     *
     * @param form   表单。
     * @param errors 参数校验错误信息
     */
    @PostMapping("/return")
    public int bookReturn(@RequestBody @Valid ReturnBookForm form, Errors errors){
        if (errors.hasErrors()) {
            // 当form中存在验证错误，则抛出业务错误，将验证信息输出。
            throw new BusinessFailureException(errors);
        }
        return bookService.returnBook(form.getHistoryId());
    }


}
