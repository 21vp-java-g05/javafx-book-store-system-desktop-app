--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: imports_book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.imports_book (
    imports_id integer NOT NULL,
    book_id integer NOT NULL,
    quantity integer NOT NULL,
    import_price real NOT NULL,
    remaining integer NOT NULL
);


ALTER TABLE public.imports_book OWNER TO postgres;

--
-- Name: defaultremaining(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.defaultremaining() RETURNS integer
    LANGUAGE sql
    RETURN (SELECT imports_book.quantity FROM public.imports_book);


ALTER FUNCTION public.defaultremaining() OWNER TO postgres;

--
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    id integer NOT NULL,
    fullname character varying(255) NOT NULL,
    mail character varying(321) NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role integer NOT NULL,
    status boolean DEFAULT true NOT NULL
);


ALTER TABLE public.account OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.account_id_seq OWNER TO postgres;

--
-- Name: account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.account_id_seq OWNED BY public.account.id;


--
-- Name: author; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.author (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    status boolean DEFAULT true NOT NULL
);


ALTER TABLE public.author OWNER TO postgres;

--
-- Name: author_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.author_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.author_id_seq OWNER TO postgres;

--
-- Name: author_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.author_id_seq OWNED BY public.author.id;


--
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    isbn character(13),
    language character varying(255),
    number_of_pages integer,
    publisher integer NOT NULL,
    author integer NOT NULL,
    status boolean DEFAULT true NOT NULL
);


ALTER TABLE public.book OWNER TO postgres;

--
-- Name: book_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.book_id_seq OWNER TO postgres;

--
-- Name: book_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.book_id_seq OWNED BY public.book.id;


--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    status boolean DEFAULT true NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: category_book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category_book (
    category_id integer NOT NULL,
    book_id integer NOT NULL
);

ALTER TABLE public.category_book
    ADD PRIMARY KEY (category_id),
ADD PRIMARY KEY (book_id);

ALTER TABLE public.category_book OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.category_id_seq OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_id_seq OWNED BY public.category.id;


--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    id integer NOT NULL,
    fullname character varying(255) NOT NULL,
    mail character varying(321) NOT NULL,
    gender boolean NOT NULL,
    status boolean DEFAULT true NOT NULL
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customer_id_seq OWNER TO postgres;

--
-- Name: customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;


--
-- Name: imports; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.imports (
    id integer NOT NULL,
    import_time timestamp without time zone NOT NULL,
    employee integer NOT NULL,
    total_cost real NOT NULL
);


ALTER TABLE public.imports OWNER TO postgres;

--
-- Name: imports_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.imports_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.imports_id_seq OWNER TO postgres;

--
-- Name: imports_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.imports_id_seq OWNED BY public.imports.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    order_time timestamp without time zone NOT NULL,
    employee integer NOT NULL,
    customer integer,
    sale_price real NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: orders_book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders_book (
    orders_id integer NOT NULL,
    book_id integer NOT NULL,
    quantity integer NOT NULL,
    price real NOT NULL
);


ALTER TABLE public.orders_book OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.orders_id_seq OWNER TO postgres;

--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- Name: publisher; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.publisher (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    status boolean DEFAULT true NOT NULL
);


ALTER TABLE public.publisher OWNER TO postgres;

--
-- Name: publisher_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.publisher_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.publisher_id_seq OWNER TO postgres;

--
-- Name: publisher_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.publisher_id_seq OWNED BY public.publisher.id;


--
-- Name: account id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account ALTER COLUMN id SET DEFAULT nextval('public.account_id_seq'::regclass);


--
-- Name: author id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author ALTER COLUMN id SET DEFAULT nextval('public.author_id_seq'::regclass);


--
-- Name: book id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book ALTER COLUMN id SET DEFAULT nextval('public.book_id_seq'::regclass);


--
-- Name: category id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN id SET DEFAULT nextval('public.category_id_seq'::regclass);


--
-- Name: customer id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);


--
-- Name: imports id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imports ALTER COLUMN id SET DEFAULT nextval('public.imports_id_seq'::regclass);


--
-- Name: imports_book remaining; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imports_book ALTER COLUMN remaining SET DEFAULT public.defaultremaining();


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- Name: publisher id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publisher ALTER COLUMN id SET DEFAULT nextval('public.publisher_id_seq'::regclass);


--
-- Name: account account_mail_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_mail_key UNIQUE (mail);


--
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- Name: author author_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: customer customer_mail_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_mail_key UNIQUE (mail);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);


--
-- Name: imports imports_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imports
    ADD CONSTRAINT imports_pkey PRIMARY KEY (id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: category_book pk_category_book; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_book
    ADD CONSTRAINT pk_category_book PRIMARY KEY (category_id, book_id);


--
-- Name: imports_book pk_import_book; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imports_book
    ADD CONSTRAINT pk_import_book PRIMARY KEY (imports_id, book_id);


--
-- Name: orders_book pk_orders_book; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders_book
    ADD CONSTRAINT pk_orders_book PRIMARY KEY (orders_id, book_id);


--
-- Name: publisher publisher_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publisher
    ADD CONSTRAINT publisher_pkey PRIMARY KEY (id);


--
-- Name: book fk_book_author; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fk_book_author FOREIGN KEY (author) REFERENCES public.author(id);


--
-- Name: book fk_book_publisher; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT fk_book_publisher FOREIGN KEY (publisher) REFERENCES public.publisher(id);


--
-- Name: category_book fk_category_book_book; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_book
    ADD CONSTRAINT fk_category_book_book FOREIGN KEY (book_id) REFERENCES public.book(id);


--
-- Name: category_book fk_category_book_category; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_book
    ADD CONSTRAINT fk_category_book_category FOREIGN KEY (category_id) REFERENCES public.category(id);


--
-- Name: imports_book fk_import_book_book; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imports_book
    ADD CONSTRAINT fk_import_book_book FOREIGN KEY (book_id) REFERENCES public.book(id);


--
-- Name: imports_book fk_import_book_import; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imports_book
    ADD CONSTRAINT fk_import_book_import FOREIGN KEY (imports_id) REFERENCES public.imports(id);


--
-- Name: imports fk_imports_account; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imports
    ADD CONSTRAINT fk_imports_account FOREIGN KEY (employee) REFERENCES public.account(id);


--
-- Name: orders fk_orders_account; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_orders_account FOREIGN KEY (employee) REFERENCES public.account(id);


--
-- Name: orders_book fk_orders_book_book; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders_book
    ADD CONSTRAINT fk_orders_book_book FOREIGN KEY (book_id) REFERENCES public.book(id);


--
-- Name: orders_book fk_orders_book_import; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders_book
    ADD CONSTRAINT fk_orders_book_import FOREIGN KEY (orders_id) REFERENCES public.orders(id);


--
-- Name: orders fk_orders_customer; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_orders_customer FOREIGN KEY (customer) REFERENCES public.customer(id);


--
-- PostgreSQL database dump complete
--

INSERT INTO public.book (id, title, isbn, language, number_of_pages, publisher, author, status) VALUES
                                                                                                    (1, 'The Great Gatsby', '9780743273565', 'English', 180, 1, 1, true),
                                                                                                    (2, 'To Kill a Mockingbird', '9780061120084', 'English', 324, 2, 2, true),
                                                                                                    (3, '1984', '9780452284234', 'English', 328, 3, 3, true),
                                                                                                    (4, 'Pride and Prejudice', '9780141439518', 'English', 279, 4, 4, true),
                                                                                                    (5, 'The Catcher in the Rye', '9780316769488', 'English', 277, 5, 5, true),
                                                                                                    (6, 'Animal Farm', '9780451526342', 'English', 141, 3, 3, true),
                                                                                                    (7, 'Brave New World', '9780060850524', 'English', 288, 6, 6, true),
                                                                                                    (8, 'Lord of the Flies', '9780571273577', 'English', 224, 7, 7, true),
                                                                                                    (9, 'The Hobbit', '9780547928227', 'English', 310, 8, 8, true),
                                                                                                    (10, 'The Lord of the Rings', '9780544003415', 'English', 1178, 9, 8, true);

INSERT INTO public.category (id, name, description, status) VALUES
                                                                (1, 'Fiction', 'Books that are not based on real events', true),
                                                                (2, 'Non-fiction', 'Books that are based on real events', true),
                                                                (3, 'Science Fiction', 'Books that involve futuristic or fantastical scientific and technological advances', true),
                                                                (4, 'Mystery', 'Books that involve a mysterious event or crime', true),
                                                                (5, 'Romance', 'Books that focus on romantic love between characters', true),
                                                                (6, 'Fantasy', 'Books that feature magical or supernatural elements', true),
                                                                (7, 'Thriller', 'Books that create excitement and suspense', true),
                                                                (8, 'Horror', 'Books that intend to scare, unsettle, or horrify the reader', true),
                                                                (9, 'Biography', 'Books that tell the life story of a real person', true),
                                                                (10, 'Self-help', 'Books that provide guidance and advice on personal growth and development', true);

INSERT INTO public.category_book (category_id, book_id) VALUES
                                                            (1, 1),
                                                            (1, 2),
                                                            (1, 3),
                                                            (1, 4),
                                                            (1, 5),
                                                            (3, 3),
                                                            (3, 6),
                                                            (3, 7),
                                                            (6, 9),
                                                            (6, 10);

INSERT INTO public.author (id, name, description, status) VALUES
                                                              (1, 'F. Scott Fitzgerald', 'American novelist and short-story writer', true),
                                                              (2, 'Harper Lee', 'American novelist best known for her 1960 novel "To Kill a Mockingbird"', true),
                                                              (3, 'George Orwell', 'English novelist, essayist, journalist, and critic', true),
                                                              (4, 'Jane Austen', 'English novelist known primarily for her six major novels', true),
                                                              (5, 'J.D. Salinger', 'American writer known for "The Catcher in the Rye"', true),
                                                              (6, 'Aldous Huxley', 'English writer and philosopher', true),
                                                              (7, 'William Golding', 'British novelist, poet, playwright, and Nobel Prize laureate', true),
                                                              (8, 'J.R.R. Tolkien', 'English writer, poet, philologist, and university professor', true);

INSERT INTO public.publisher (id, name, description, status) VALUES
                                                                 (1, 'Scribner', 'An American publisher based in New York City', true),
                                                                 (2, 'HarperCollins', 'One of the world''s largest publishing companies', true),
                                                                 (3, 'Signet Classics', 'An imprint of Penguin Books', true),
                                                                 (4, 'Penguin Classics', 'An imprint published by Penguin Books', true),
                                                                 (5, 'Little, Brown and Company', 'An American publisher founded in 1837', true),
                                                                 (6, 'Harper Perennial', 'A paperback imprint of the publishing house HarperCollins', true),
                                                                 (7, 'Faber and Faber', 'An independent publishing house in London, England', true),
                                                                 (8, 'Houghton Mifflin Harcourt', 'An American publishing company based in Boston, Massachusetts', true),
                                                                 (9, 'Allen & Unwin', 'An independent publishing company based in Australia', true),
                                                                 (10, 'Ballantine Books', 'An American publisher', true);