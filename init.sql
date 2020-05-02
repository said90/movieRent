--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1 (Debian 12.1-1.pgdg100+1)
-- Dumped by pg_dump version 12.1

-- Started on 2020-05-02 16:24:18

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

--
-- TOC entry 3044 (class 1262 OID 16384)
-- Name: movierent; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE movierent WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE movierent OWNER TO postgres;

\connect movierent

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


--
-- TOC entry 203 (class 1259 OID 16549)
-- Name: event_logs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.event_logs (
    id_event_log integer NOT NULL,
    date timestamp without time zone,
    event_type character varying(255),
    qty integer,
    id_movie integer NOT NULL,
    id_user integer NOT NULL
);


ALTER TABLE public.event_logs OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16547)
-- Name: event_logs_id_event_log_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.event_logs_id_event_log_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.event_logs_id_event_log_seq OWNER TO postgres;

--
-- TOC entry 3045 (class 0 OID 0)
-- Dependencies: 202
-- Name: event_logs_id_event_log_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.event_logs_id_event_log_seq OWNED BY public.event_logs.id_event_log;


--
-- TOC entry 205 (class 1259 OID 16557)
-- Name: movie_imgs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movie_imgs (
    id_movie_imag integer NOT NULL,
    created_date timestamp without time zone,
    photo character varying(255),
    id_movie integer NOT NULL
);


ALTER TABLE public.movie_imgs OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16555)
-- Name: movie_imgs_id_movie_imag_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movie_imgs_id_movie_imag_seq
    
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movie_imgs_id_movie_imag_seq OWNER TO postgres;

--
-- TOC entry 3046 (class 0 OID 0)
-- Dependencies: 204
-- Name: movie_imgs_id_movie_imag_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movie_imgs_id_movie_imag_seq OWNED BY public.movie_imgs.id_movie_imag;


--
-- TOC entry 207 (class 1259 OID 16565)
-- Name: movie_likes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movie_likes (
    id_movie_like integer NOT NULL,
    id_movie integer NOT NULL,
    id_user integer NOT NULL
);


ALTER TABLE public.movie_likes OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16563)
-- Name: movie_likes_id_movie_like_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movie_likes_id_movie_like_seq
    
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movie_likes_id_movie_like_seq OWNER TO postgres;

--
-- TOC entry 3047 (class 0 OID 0)
-- Dependencies: 206
-- Name: movie_likes_id_movie_like_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movie_likes_id_movie_like_seq OWNED BY public.movie_likes.id_movie_like;


--
-- TOC entry 209 (class 1259 OID 16573)
-- Name: movies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movies (
    id_movie integer NOT NULL,
    availability character varying(255),
    description character varying(1000),
    likes integer,
    removed_date timestamp without time zone,
    rent_price numeric(19,2),
    sale_price numeric(19,2),
    title character varying(255)
);


ALTER TABLE public.movies OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16571)
-- Name: movies_id_movie_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.movies_id_movie_seq
    
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.movies_id_movie_seq OWNER TO postgres;

--
-- TOC entry 3048 (class 0 OID 0)
-- Dependencies: 208
-- Name: movies_id_movie_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.movies_id_movie_seq OWNED BY public.movies.id_movie;


--
-- TOC entry 222 (class 1259 OID 16708)
-- Name: oauth_access_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.oauth_access_token (
    token_id character varying(256),
    token bytea,
    authentication_id character varying(256),
    user_name character varying(256),
    client_id character varying(256),
    authentication bytea,
    refresh_token character varying(256)
);


ALTER TABLE public.oauth_access_token OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16714)
-- Name: oauth_refresh_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.oauth_refresh_token (
    token_id character varying(256),
    token bytea,
    authentication bytea
);


ALTER TABLE public.oauth_refresh_token OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16584)
-- Name: rental_movies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rental_movies (
    id_rental_movie integer NOT NULL,
    due_date timestamp without time zone,
    penalty boolean,
    penalty_recharge numeric(19,2),
    rental_date timestamp without time zone,
    return_date timestamp without time zone,
    id_stock_movie integer NOT NULL,
    id_user integer NOT NULL
);


ALTER TABLE public.rental_movies OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16582)
-- Name: rental_movies_id_rental_movie_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.rental_movies_id_rental_movie_seq
    
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rental_movies_id_rental_movie_seq OWNER TO postgres;

--
-- TOC entry 3049 (class 0 OID 0)
-- Dependencies: 210
-- Name: rental_movies_id_rental_movie_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.rental_movies_id_rental_movie_seq OWNED BY public.rental_movies.id_rental_movie;


--
-- TOC entry 212 (class 1259 OID 16590)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id_role integer NOT NULL,
    description character varying(255),
    name character varying(255)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16600)
-- Name: sale_movies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sale_movies (
    id_sale_movie integer NOT NULL,
    date timestamp without time zone,
    sale_price numeric(19,2),
    id_stock_movie integer NOT NULL,
    id_user integer NOT NULL
);


ALTER TABLE public.sale_movies OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16598)
-- Name: sale_movies_id_sale_movie_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sale_movies_id_sale_movie_seq
    
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sale_movies_id_sale_movie_seq OWNER TO postgres;

--
-- TOC entry 3050 (class 0 OID 0)
-- Dependencies: 213
-- Name: sale_movies_id_sale_movie_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sale_movies_id_sale_movie_seq OWNED BY public.sale_movies.id_sale_movie;


--
-- TOC entry 216 (class 1259 OID 16608)
-- Name: stock_movies; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.stock_movies (
    id_stock_movie integer NOT NULL,
    availability character varying(255),
    id_movie integer NOT NULL
);


ALTER TABLE public.stock_movies OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16606)
-- Name: stock_movies_id_stock_movie_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.stock_movies_id_stock_movie_seq
    
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stock_movies_id_stock_movie_seq OWNER TO postgres;

--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 215
-- Name: stock_movies_id_stock_movie_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.stock_movies_id_stock_movie_seq OWNED BY public.stock_movies.id_stock_movie;


--
-- TOC entry 218 (class 1259 OID 16616)
-- Name: update_logs; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.update_logs (
    id_update_log integer NOT NULL,
    date timestamp without time zone,
    rent_price numeric(19,2),
    sale_price numeric(19,2),
    title character varying(150),
    id_movie integer NOT NULL,
    id_user integer NOT NULL
);


ALTER TABLE public.update_logs OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16614)
-- Name: update_logs_id_update_log_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.update_logs_id_update_log_seq
    
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.update_logs_id_update_log_seq OWNER TO postgres;

--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 217
-- Name: update_logs_id_update_log_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.update_logs_id_update_log_seq OWNED BY public.update_logs.id_update_log;


--
-- TOC entry 219 (class 1259 OID 16622)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    id_user integer NOT NULL,
    id_role integer NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16627)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id_user integer NOT NULL,
    enabled boolean NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16625)
-- Name: users_id_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_user_seq
    
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_user_seq OWNER TO postgres;

--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 220
-- Name: users_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_user_seq OWNED BY public.users.id_user;


--
-- TOC entry 2846 (class 2604 OID 16552)
-- Name: event_logs id_event_log; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_logs ALTER COLUMN id_event_log SET DEFAULT nextval('public.event_logs_id_event_log_seq'::regclass);


--
-- TOC entry 2847 (class 2604 OID 16560)
-- Name: movie_imgs id_movie_imag; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_imgs ALTER COLUMN id_movie_imag SET DEFAULT nextval('public.movie_imgs_id_movie_imag_seq'::regclass);


--
-- TOC entry 2848 (class 2604 OID 16568)
-- Name: movie_likes id_movie_like; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_likes ALTER COLUMN id_movie_like SET DEFAULT nextval('public.movie_likes_id_movie_like_seq'::regclass);


--
-- TOC entry 2849 (class 2604 OID 16576)
-- Name: movies id_movie; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies ALTER COLUMN id_movie SET DEFAULT nextval('public.movies_id_movie_seq'::regclass);


--
-- TOC entry 2850 (class 2604 OID 16587)
-- Name: rental_movies id_rental_movie; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental_movies ALTER COLUMN id_rental_movie SET DEFAULT nextval('public.rental_movies_id_rental_movie_seq'::regclass);


--
-- TOC entry 2851 (class 2604 OID 16603)
-- Name: sale_movies id_sale_movie; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_movies ALTER COLUMN id_sale_movie SET DEFAULT nextval('public.sale_movies_id_sale_movie_seq'::regclass);


--
-- TOC entry 2852 (class 2604 OID 16611)
-- Name: stock_movies id_stock_movie; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock_movies ALTER COLUMN id_stock_movie SET DEFAULT nextval('public.stock_movies_id_stock_movie_seq'::regclass);


--
-- TOC entry 2853 (class 2604 OID 16619)
-- Name: update_logs id_update_log; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.update_logs ALTER COLUMN id_update_log SET DEFAULT nextval('public.update_logs_id_update_log_seq'::regclass);


--
-- TOC entry 2854 (class 2604 OID 16630)
-- Name: users id_user; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id_user SET DEFAULT nextval('public.users_id_user_seq'::regclass);


--
-- TOC entry 3018 (class 0 OID 16549)
-- Dependencies: 203
-- Data for Name: event_logs; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3020 (class 0 OID 16557)
-- Dependencies: 205
-- Data for Name: movie_imgs; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3022 (class 0 OID 16565)
-- Dependencies: 207
-- Data for Name: movie_likes; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3024 (class 0 OID 16573)
-- Dependencies: 209
-- Data for Name: movies; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3037 (class 0 OID 16708)
-- Dependencies: 222
-- Data for Name: oauth_access_token; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3038 (class 0 OID 16714)
-- Dependencies: 223
-- Data for Name: oauth_refresh_token; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3026 (class 0 OID 16584)
-- Dependencies: 211
-- Data for Name: rental_movies; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3027 (class 0 OID 16590)
-- Dependencies: 212
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.roles VALUES (1, 'Administrator', 'ADMIN');
INSERT INTO public.roles VALUES (2, 'User', 'USER');


--
-- TOC entry 3029 (class 0 OID 16600)
-- Dependencies: 214
-- Data for Name: sale_movies; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3031 (class 0 OID 16608)
-- Dependencies: 216
-- Data for Name: stock_movies; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3033 (class 0 OID 16616)
-- Dependencies: 218
-- Data for Name: update_logs; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3034 (class 0 OID 16622)
-- Dependencies: 219
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_roles VALUES (1, 1);
INSERT INTO public.user_roles VALUES (2, 2);


--
-- TOC entry 3036 (class 0 OID 16627)
-- Dependencies: 221
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES (1, true, '$2a$10$mRUrTuGAsZJSvVnU59Dr9uOpSrumrTtfyyBsmZDEI6mwenRBgK/qO', 'admin');
INSERT INTO public.users VALUES (2, true, '$2a$10$Af2qbVQRpCFchv95MVoHauZONPoVWt4NFOvqVZj6xdrCYWVROg7RC', 'user');


--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 202
-- Name: event_logs_id_event_log_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.event_logs_id_event_log_seq', 1, false);


--
-- TOC entry 3055 (class 0 OID 0)
-- Dependencies: 204
-- Name: movie_imgs_id_movie_imag_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movie_imgs_id_movie_imag_seq', 1, false);


--
-- TOC entry 3056 (class 0 OID 0)
-- Dependencies: 206
-- Name: movie_likes_id_movie_like_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movie_likes_id_movie_like_seq', 1, false);


--
-- TOC entry 3057 (class 0 OID 0)
-- Dependencies: 208
-- Name: movies_id_movie_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.movies_id_movie_seq', 1, false);


--
-- TOC entry 3058 (class 0 OID 0)
-- Dependencies: 210
-- Name: rental_movies_id_rental_movie_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rental_movies_id_rental_movie_seq', 1, false);


--
-- TOC entry 3059 (class 0 OID 0)
-- Dependencies: 213
-- Name: sale_movies_id_sale_movie_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sale_movies_id_sale_movie_seq', 1, false);


--
-- TOC entry 3060 (class 0 OID 0)
-- Dependencies: 215
-- Name: stock_movies_id_stock_movie_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.stock_movies_id_stock_movie_seq', 1, false);


--
-- TOC entry 3061 (class 0 OID 0)
-- Dependencies: 217
-- Name: update_logs_id_update_log_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.update_logs_id_update_log_seq', 1, false);


--
-- TOC entry 3062 (class 0 OID 0)
-- Dependencies: 220
-- Name: users_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_user_seq', 1, false);


--
-- TOC entry 2856 (class 2606 OID 16554)
-- Name: event_logs event_logs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_logs
    ADD CONSTRAINT event_logs_pkey PRIMARY KEY (id_event_log);


--
-- TOC entry 2858 (class 2606 OID 16562)
-- Name: movie_imgs movie_imgs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_imgs
    ADD CONSTRAINT movie_imgs_pkey PRIMARY KEY (id_movie_imag);


--
-- TOC entry 2860 (class 2606 OID 16570)
-- Name: movie_likes movie_likes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_likes
    ADD CONSTRAINT movie_likes_pkey PRIMARY KEY (id_movie_like);


--
-- TOC entry 2862 (class 2606 OID 16581)
-- Name: movies movies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movies
    ADD CONSTRAINT movies_pkey PRIMARY KEY (id_movie);


--
-- TOC entry 2864 (class 2606 OID 16589)
-- Name: rental_movies rental_movies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental_movies
    ADD CONSTRAINT rental_movies_pkey PRIMARY KEY (id_rental_movie);


--
-- TOC entry 2866 (class 2606 OID 16597)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id_role);


--
-- TOC entry 2868 (class 2606 OID 16605)
-- Name: sale_movies sale_movies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_movies
    ADD CONSTRAINT sale_movies_pkey PRIMARY KEY (id_sale_movie);


--
-- TOC entry 2870 (class 2606 OID 16613)
-- Name: stock_movies stock_movies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock_movies
    ADD CONSTRAINT stock_movies_pkey PRIMARY KEY (id_stock_movie);


--
-- TOC entry 2874 (class 2606 OID 16637)
-- Name: users uk_r43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 2872 (class 2606 OID 16621)
-- Name: update_logs update_logs_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.update_logs
    ADD CONSTRAINT update_logs_pkey PRIMARY KEY (id_update_log);


--
-- TOC entry 2876 (class 2606 OID 16635)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id_user);


--
-- TOC entry 2889 (class 2606 OID 16698)
-- Name: user_roles fk1v995xldvmr6w96c5feofx1gf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fk1v995xldvmr6w96c5feofx1gf FOREIGN KEY (id_role) REFERENCES public.roles(id_role);


--
-- TOC entry 2880 (class 2606 OID 16653)
-- Name: movie_likes fk2a0vb0nuwg3naf8kd2lxvyur4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_likes
    ADD CONSTRAINT fk2a0vb0nuwg3naf8kd2lxvyur4 FOREIGN KEY (id_movie) REFERENCES public.movies(id_movie);


--
-- TOC entry 2878 (class 2606 OID 16643)
-- Name: event_logs fk324ms88afqnyb134efer5vt8g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_logs
    ADD CONSTRAINT fk324ms88afqnyb134efer5vt8g FOREIGN KEY (id_user) REFERENCES public.users(id_user);


--
-- TOC entry 2885 (class 2606 OID 16678)
-- Name: sale_movies fk3eq512gtfht1guua4qprcym2k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_movies
    ADD CONSTRAINT fk3eq512gtfht1guua4qprcym2k FOREIGN KEY (id_user) REFERENCES public.users(id_user);


--
-- TOC entry 2877 (class 2606 OID 16638)
-- Name: event_logs fk6rpm7g0ev7gnl8j7qtwee5fl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.event_logs
    ADD CONSTRAINT fk6rpm7g0ev7gnl8j7qtwee5fl FOREIGN KEY (id_movie) REFERENCES public.movies(id_movie);


--
-- TOC entry 2890 (class 2606 OID 16703)
-- Name: user_roles fk9ihrn1kwsu0a99doxpm7jbkdb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fk9ihrn1kwsu0a99doxpm7jbkdb FOREIGN KEY (id_user) REFERENCES public.users(id_user);


--
-- TOC entry 2887 (class 2606 OID 16688)
-- Name: update_logs fk_movie; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.update_logs
    ADD CONSTRAINT fk_movie FOREIGN KEY (id_movie) REFERENCES public.movies(id_movie);


--
-- TOC entry 2888 (class 2606 OID 16693)
-- Name: update_logs fk_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.update_logs
    ADD CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES public.users(id_user);


--
-- TOC entry 2881 (class 2606 OID 16658)
-- Name: movie_likes fkd7axbtwt9fdyb7xm0h1p0qo6c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_likes
    ADD CONSTRAINT fkd7axbtwt9fdyb7xm0h1p0qo6c FOREIGN KEY (id_user) REFERENCES public.users(id_user);


--
-- TOC entry 2879 (class 2606 OID 16648)
-- Name: movie_imgs fkgy3g9nedbwwci2qivw9alht6x; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movie_imgs
    ADD CONSTRAINT fkgy3g9nedbwwci2qivw9alht6x FOREIGN KEY (id_movie) REFERENCES public.movies(id_movie);


--
-- TOC entry 2883 (class 2606 OID 16668)
-- Name: rental_movies fkjtmb90onrucudvxogrgq8yegx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental_movies
    ADD CONSTRAINT fkjtmb90onrucudvxogrgq8yegx FOREIGN KEY (id_user) REFERENCES public.users(id_user);


--
-- TOC entry 2886 (class 2606 OID 16683)
-- Name: stock_movies fkl7shfv3jgh0stlscdt2g8qn2f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.stock_movies
    ADD CONSTRAINT fkl7shfv3jgh0stlscdt2g8qn2f FOREIGN KEY (id_movie) REFERENCES public.movies(id_movie);


--
-- TOC entry 2882 (class 2606 OID 16663)
-- Name: rental_movies fkr6o5wvms7yw9nojsx72pe0jgk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rental_movies
    ADD CONSTRAINT fkr6o5wvms7yw9nojsx72pe0jgk FOREIGN KEY (id_stock_movie) REFERENCES public.stock_movies(id_stock_movie);


--
-- TOC entry 2884 (class 2606 OID 16673)
-- Name: sale_movies fks8dg11rxkm4r601clae07pewt; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sale_movies
    ADD CONSTRAINT fks8dg11rxkm4r601clae07pewt FOREIGN KEY (id_stock_movie) REFERENCES public.stock_movies(id_stock_movie);


-- Completed on 2020-05-02 16:24:19

--
-- PostgreSQL database dump complete
--

