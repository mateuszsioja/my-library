package com.msioja.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BookDTO {

    private Long bookId;

    @NotNull
    @Size(min = 2, max = 25)
    private String serialNumber;

    @NotNull
    @Size(min = 2, max = 25)
    private String author;

    @NotNull
    @Size(min = 2, max = 25)
    private String title;

    @NotNull
    @Size(min = 2, max = 25)
    private String category;

    private Long userId;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
