--
-- PostgreSQL database dump
--

-- Dumped from database version 10.6
-- Dumped by pg_dump version 10.6

-- Started on 2018-12-28 21:52:33

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 596 (class 1247 OID 16529)
-- Name: level_enum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.level_enum AS ENUM (
    'FATAL',
    'ERROR',
    'WARN',
    'INFO',
    'DEBUG',
    'TRACE',
    'ASSERT'
);


ALTER TYPE public.level_enum OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16506)
-- Name: entity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.entity (
    id smallint NOT NULL,
    name character varying(64) NOT NULL,
    log_key character(32) NOT NULL
);


ALTER TABLE public.entity OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16504)
-- Name: entity_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.entity_id_seq
    AS smallint
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.entity_id_seq OWNER TO postgres;

--
-- TOC entry 2817 (class 0 OID 0)
-- Dependencies: 196
-- Name: entity_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.entity_id_seq OWNED BY public.entity.id;


--
-- TOC entry 199 (class 1259 OID 16514)
-- Name: log; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.log (
    id bigint NOT NULL,
    entity integer,
    storage_date timestamp without time zone DEFAULT now() NOT NULL,
    record_date timestamp without time zone NOT NULL,
    "UserAgent" character varying(256) DEFAULT NULL::character varying,
    "Host" character varying(256) DEFAULT NULL::character varying,
    message character varying(1024) NOT NULL,
    http_code smallint,
    level public.level_enum NOT NULL
);


ALTER TABLE public.log OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16512)
-- Name: log_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.log_id_seq OWNER TO postgres;

--
-- TOC entry 2818 (class 0 OID 0)
-- Dependencies: 198
-- Name: log_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.log_id_seq OWNED BY public.log.id;


--
-- TOC entry 2680 (class 2604 OID 16509)
-- Name: entity id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entity ALTER COLUMN id SET DEFAULT nextval('public.entity_id_seq'::regclass);


--
-- TOC entry 2681 (class 2604 OID 16517)
-- Name: log id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log ALTER COLUMN id SET DEFAULT nextval('public.log_id_seq'::regclass);


--
-- TOC entry 2686 (class 2606 OID 16511)
-- Name: entity entity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.entity
    ADD CONSTRAINT entity_pkey PRIMARY KEY (id);


--
-- TOC entry 2688 (class 2606 OID 16522)
-- Name: log log_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_pkey PRIMARY KEY (id);


--
-- TOC entry 2689 (class 2606 OID 16523)
-- Name: log log_entity_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.log
    ADD CONSTRAINT log_entity_fk FOREIGN KEY (entity) REFERENCES public.entity(id);


-- Completed on 2018-12-28 21:52:33

--
-- PostgreSQL database dump complete
--

