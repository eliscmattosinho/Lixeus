--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

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
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


--
-- Name: statuscompraenum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.statuscompraenum AS ENUM (
    'cancelada',
    'efetuada',
    'em confirmação'
);


ALTER TYPE public.statuscompraenum OWNER TO postgres;

--
-- Name: ticketstatusenum; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.ticketstatusenum AS ENUM (
    'disponível',
    'vencido',
    'cancelado'
);


ALTER TYPE public.ticketstatusenum OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: atracoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.atracoes (
    id_atracao integer NOT NULL,
    descricao character varying(300),
    data date,
    hora_ini time without time zone,
    hora_fim time without time zone,
    max_ticket integer,
    max_cliente_ticket integer,
    preco_ticket double precision,
    titulo text
);


ALTER TABLE public.atracoes OWNER TO postgres;

--
-- Name: atracoes_id_atracao_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.atracoes_id_atracao_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.atracoes_id_atracao_seq OWNER TO postgres;

--
-- Name: atracoes_id_atracao_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.atracoes_id_atracao_seq OWNED BY public.atracoes.id_atracao;


--
-- Name: clientes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clientes (
    id_cliente integer NOT NULL,
    nome character varying(100),
    email character varying(100),
    cpf character varying(11),
    tel character varying(20),
    data_nasc date,
    senha character varying(255),
    ativo boolean DEFAULT true
);


ALTER TABLE public.clientes OWNER TO postgres;

--
-- Name: clientes_id_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clientes_id_cliente_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.clientes_id_cliente_seq OWNER TO postgres;

--
-- Name: clientes_id_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clientes_id_cliente_seq OWNED BY public.clientes.id_cliente;


--
-- Name: compras; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.compras (
    id_compra integer NOT NULL,
    cliente_id integer,
    data_compra date,
    forma_pag character varying,
    qtd_ticket integer,
    status_compra character varying,
    total double precision
);


ALTER TABLE public.compras OWNER TO postgres;

--
-- Name: compras_id_compra_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.compras_id_compra_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.compras_id_compra_seq OWNER TO postgres;

--
-- Name: compras_id_compra_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.compras_id_compra_seq OWNED BY public.compras.id_compra;


--
-- Name: tickets; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tickets (
    id_ticket integer NOT NULL,
    atracao_id integer,
    ticket_status public.ticketstatusenum,
    compra_id integer,
    codigo_acesso character varying(6)
);


ALTER TABLE public.tickets OWNER TO postgres;

--
-- Name: tickets_id_ticket_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tickets_id_ticket_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.tickets_id_ticket_seq OWNER TO postgres;

--
-- Name: tickets_id_ticket_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tickets_id_ticket_seq OWNED BY public.tickets.id_ticket;


--
-- Name: ticketsporcompra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ticketsporcompra (
    ticket_id integer NOT NULL,
    compra_id integer NOT NULL
);


ALTER TABLE public.ticketsporcompra OWNER TO postgres;

--
-- Name: atracoes id_atracao; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.atracoes ALTER COLUMN id_atracao SET DEFAULT nextval('public.atracoes_id_atracao_seq'::regclass);


--
-- Name: clientes id_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes ALTER COLUMN id_cliente SET DEFAULT nextval('public.clientes_id_cliente_seq'::regclass);


--
-- Name: compras id_compra; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras ALTER COLUMN id_compra SET DEFAULT nextval('public.compras_id_compra_seq'::regclass);


--
-- Name: tickets id_ticket; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets ALTER COLUMN id_ticket SET DEFAULT nextval('public.tickets_id_ticket_seq'::regclass);


--
-- Data for Name: atracoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (5, 'Exploração dos Planetas do Sistema Solar', '2024-07-20', '10:00:00', '12:00:00', 200, 4, 50, 'Viagem pelo Sistema Solar');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (6, 'Descubra as Constelações do Hemisfério Norte', '2024-07-21', '15:00:00', '17:00:00', 150, 3, 45, 'Constelações do Hemisfério Norte');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (7, 'A História do Universo: Do Big Bang aos Dias Atuais', '2024-07-22', '14:00:00', '16:00:00', 250, 5, 55, 'História do Universo');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (9, 'Vida em Outros Planetas: Mito ou Realidade?', '2024-07-24', '13:00:00', '15:00:00', 200, 4, 40, 'Busca por Vida Extraterrestre');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (8, 'Observação de Estrelas e Planetas com Telescópios', '2024-07-23', '20:00:00', '22:00:00', 95, 2, 60, 'Noite de Observação Astronômica');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (10, 'A Física dos Buracos Negros', '2024-07-25', '16:00:00', '18:00:00', 176, 3, 65, 'Mistérios dos Buracos Negros');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (11, 'O Futuro da Exploração Espacial', '2024-07-26', '11:00:00', '13:00:00', 200, 5, 70, 'Exploração Espacial Futurista');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (12, 'O Céu Noturno: Identificação de Estrelas e Constelações', '2024-07-27', '19:00:00', '21:00:00', 108, 3, 50, 'Guia do Céu Noturno');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (13, 'Uma fascinante viagem pelo nosso sistema solar, explorando planetas, luas e mais.', '2024-07-01', '18:00:00', '19:30:00', 100, 4, 30, 'Viagem pelo Sistema Solar');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (14, 'Descubra os mistérios das estrelas e constelações em uma noite deslumbrante.', '2024-07-02', '20:00:00', '21:30:00', 120, 4, 25, 'Noite das Estrelas');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (15, 'Explore as profundezas do universo e aprenda sobre galáxias distantes.', '2024-07-03', '17:00:00', '18:30:00', 80, 4, 35, 'Galáxias Distantes');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (16, 'Saiba mais sobre a história e as missões da exploração espacial.', '2024-07-04', '19:00:00', '20:30:00', 90, 4, 28, 'Exploração Espacial');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (17, 'Uma viagem educativa e divertida para as crianças aprenderem sobre os planetas.', '2024-07-05', '16:00:00', '17:00:00', 150, 4, 20, 'Planetas para Crianças');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (18, 'Entenda como funcionam os buracos negros e outros fenômenos cósmicos.', '2024-07-06', '18:00:00', '19:30:00', 110, 4, 32, 'Misteriosos Buracos Negros');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (19, 'Conheça as diferentes fases da lua e seus impactos na Terra.', '2024-07-07', '20:00:00', '21:30:00', 100, 4, 27, 'Fases da Lua');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (20, 'Viaje no tempo para descobrir a origem do universo com o Big Bang.', '2024-07-08', '19:00:00', '20:30:00', 95, 4, 33, 'O Big Bang');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (22, 'Uma sessão especial sobre meteoros e suas características incríveis.', '2024-07-10', '18:00:00', '19:30:00', 85, 4, 29, 'Meteoros: Pedras do Espaço');
INSERT INTO public.atracoes (id_atracao, descricao, data, hora_ini, hora_fim, max_ticket, max_cliente_ticket, preco_ticket, titulo) VALUES (21, 'Aprenda sobre a importância do Sol e seu papel no nosso sistema solar.', '2024-07-09', '17:00:00', '18:30:00', 114, 4, 26, 'O Sol e o Sistema Solar');


--
-- Data for Name: clientes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.clientes (id_cliente, nome, email, cpf, tel, data_nasc, senha, ativo) VALUES (19, 'Polly Pocket', 'pocket@fatec.sp.gov.br', '5058594678', '1499037859', '2003-02-20', '8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', false);
INSERT INTO public.clientes (id_cliente, nome, email, cpf, tel, data_nasc, senha, ativo) VALUES (20, 'Elis Mattosinho', 'elis.mattosinho@fatec.sp.gov.br', '5058594034', '5436657567', '2006-09-30', '8bb0cf6eb9b17d0f7d22b456f121257dc1254e1f01665370476383ea776df414', true);


--
-- Data for Name: compras; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.compras (id_compra, cliente_id, data_compra, forma_pag, qtd_ticket, status_compra, total) VALUES (48, 19, '2024-06-19', 'PIX', 6, 'confirmando', 156);


--
-- Data for Name: tickets; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tickets (id_ticket, atracao_id, ticket_status, compra_id, codigo_acesso) VALUES (155, 21, 'disponível', 48, 'WUTLYB');
INSERT INTO public.tickets (id_ticket, atracao_id, ticket_status, compra_id, codigo_acesso) VALUES (156, 21, 'disponível', 48, '639QBE');
INSERT INTO public.tickets (id_ticket, atracao_id, ticket_status, compra_id, codigo_acesso) VALUES (157, 21, 'disponível', 48, 'RQU36P');
INSERT INTO public.tickets (id_ticket, atracao_id, ticket_status, compra_id, codigo_acesso) VALUES (158, 21, 'disponível', 48, 'OI72N2');
INSERT INTO public.tickets (id_ticket, atracao_id, ticket_status, compra_id, codigo_acesso) VALUES (159, 21, 'disponível', 48, 'LFD1SW');
INSERT INTO public.tickets (id_ticket, atracao_id, ticket_status, compra_id, codigo_acesso) VALUES (160, 21, 'disponível', 48, '3U352I');


--
-- Data for Name: ticketsporcompra; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ticketsporcompra (ticket_id, compra_id) VALUES (155, 48);
INSERT INTO public.ticketsporcompra (ticket_id, compra_id) VALUES (156, 48);
INSERT INTO public.ticketsporcompra (ticket_id, compra_id) VALUES (157, 48);
INSERT INTO public.ticketsporcompra (ticket_id, compra_id) VALUES (158, 48);
INSERT INTO public.ticketsporcompra (ticket_id, compra_id) VALUES (159, 48);
INSERT INTO public.ticketsporcompra (ticket_id, compra_id) VALUES (160, 48);


--
-- Name: atracoes_id_atracao_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.atracoes_id_atracao_seq', 22, true);


--
-- Name: clientes_id_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clientes_id_cliente_seq', 20, true);


--
-- Name: compras_id_compra_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.compras_id_compra_seq', 48, true);


--
-- Name: tickets_id_ticket_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tickets_id_ticket_seq', 160, true);


--
-- Name: atracoes atracoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.atracoes
    ADD CONSTRAINT atracoes_pkey PRIMARY KEY (id_atracao);


--
-- Name: clientes clientes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clientes
    ADD CONSTRAINT clientes_pkey PRIMARY KEY (id_cliente);


--
-- Name: ticketsporcompra pk_ticket_compra; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticketsporcompra
    ADD CONSTRAINT pk_ticket_compra PRIMARY KEY (ticket_id, compra_id);


--
-- Name: compras pkcompra; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT pkcompra PRIMARY KEY (id_compra);


--
-- Name: tickets tickets_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_pkey PRIMARY KEY (id_ticket);


--
-- Name: compras fk_cliente; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.compras
    ADD CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES public.clientes(id_cliente) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED NOT VALID;


--
-- Name: tickets fk_compra_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT fk_compra_id FOREIGN KEY (compra_id) REFERENCES public.compras(id_compra);


--
-- Name: ticketsporcompra fkcompra; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticketsporcompra
    ADD CONSTRAINT fkcompra FOREIGN KEY (compra_id) REFERENCES public.compras(id_compra) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED NOT VALID;


--
-- Name: tickets tickets_atracao_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tickets
    ADD CONSTRAINT tickets_atracao_id_fkey FOREIGN KEY (atracao_id) REFERENCES public.atracoes(id_atracao);


--
-- Name: ticketsporcompra ticketsporcompra_ticket_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticketsporcompra
    ADD CONSTRAINT ticketsporcompra_ticket_id_fkey FOREIGN KEY (ticket_id) REFERENCES public.tickets(id_ticket);


--
-- PostgreSQL database dump complete
--

