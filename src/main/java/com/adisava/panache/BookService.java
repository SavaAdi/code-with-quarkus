package com.adisava.panache;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class BookService {

    // tag::listAll[]
    @GET
    public List<Book> getAllBooks() {
        return Book.listAll();
    }
    // end::listAll[]

    // tag::findById[]
    @GET
    @Path("/byId/{id}")
    public Book getBookById(@PathParam(value = "id") Long id) {
        return Book.findById(id);
    }
    // end::findById[]

    @GET
    @Path("/byTitle/{title}")
    public Book getBookByTitle(@PathParam(value = "title") String title) {
        return Book.findByTitle(title);
    }

    @GET
    @Path("/byAuthor/{author}")
    public List<Book> getBooksByAuthor(@PathParam(value = "author") String author) {
        return Book.findByAuthor(author);
    }

    @GET
    @Path("/byIsbn/{isbn}")
    public List<Book> getBooksByIsbn(@PathParam(value = "isbn") String isbn) {
        return Book.findByIsbn(isbn);
    }

    @GET
    @Path("/example")
    public Book getExampleBook() {
        var book = new Book();
        book.author = "Awesome one";
        book.isbn = "A true isbn";
        book.title = "There can be only one";
        book.addTopic("Jojo");
        book.addTopic("Dio");
        return book;
    }

    @POST
    public Response newBook(Book book) {
        book.persist();
        return Response.created(URI.create("/book/byId/" + book.id)).build();
    }

    public List<Book> pageAuthor(String author) {
        PanacheQuery<Book> authors = Book.find("author", author);
        return authors.page(Page.of(3, 25)).list();
    }
}
