--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.17
-- Dumped by pg_dump version 9.3.11
-- Started on 2018-08-27 10:43:39 EAT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;


--
-- TOC entry 172 (class 1259 OID 321865)
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 173 (class 1259 OID 321867)
-- Name: account_idart_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE account_idart_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 174 (class 1259 OID 321869)
-- Name: adresse; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE adresse (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 175 (class 1259 OID 321872)
-- Name: ag_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE ag_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 176 (class 1259 OID 321874)
-- Name: agent; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE agent (
    idagent bigint primary key,
    active boolean NOT NULL,
    im bigint,
    nomagent character varying(255),
    password character varying(255) NOT NULL,
    prenomagent character varying(255),
    idbureau bigint,
    iddirection bigint,
    idposteny bigint,
    idrole integer,
    idservice bigint
);


--
-- TOC entry 177 (class 1259 OID 321880)
-- Name: article; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE article (
    typeart integer NOT NULL,
    idarticle bigint NOT NULL,
    caracteristiquearticle character varying(255),
    especeunit character varying(255),
    nombre bigint,
    origine character varying(255),
    prix real,
    reference character varying(255),
    validation boolean NOT NULL,
    imbeneficiaire bigint,
    idcode bigint,
    imdepositaire bigint,
    iddirectionart bigint,
    idmarqueart bigint,
    idfin bigint,
    idfournisseur bigint,
    idmodacqart bigint
);


--
-- TOC entry 178 (class 1259 OID 321886)
-- Name: bureau; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE bureau (
    id bigint NOT NULL,
    designation character varying(255),
    codebureau character varying(255)
);


--
-- TOC entry 179 (class 1259 OID 321892)
-- Name: categoriemat; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE categoriemat (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 180 (class 1259 OID 321895)
-- Name: codearticle; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE codearticle (
    id bigint NOT NULL,
    designation character varying(255),
    idtypeobj bigint
);


--
-- TOC entry 181 (class 1259 OID 321898)
-- Name: designation; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE designation (
    iddesignation bigint NOT NULL,
    anneeacquisition character varying(255),
    autre character varying(255),
    documentpath character varying(255),
    especeunite character varying(255),
    image bytea,
    origine character varying(255),
    pu real,
    reffacture character varying(255),
    renseignement character varying(255),
    idcar integer,
    idfin bigint,
    idfourn bigint,
    idmarque bigint,
    idmodacq bigint,
    idnom bigint,
    idtypemateriel bigint
);


--
-- TOC entry 182 (class 1259 OID 321904)
-- Name: designation_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE designation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 183 (class 1259 OID 321906)
-- Name: devise; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE devise (
    id bigint NOT NULL,
    designation character varying(255),
    dateexpire date
);


--
-- TOC entry 184 (class 1259 OID 321909)
-- Name: direction; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE direction (
    id bigint NOT NULL,
    designation character varying(255),
    budget character varying(255),
    codedirection character varying(255),
    quatre character varying(255),
    trois character varying(255)
);


--
-- TOC entry 215 (class 1259 OID 322469)
-- Name: directionhistor; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE directionhistor (
    dirid bigint NOT NULL,
    dirtitleid bigint NOT NULL
);


--
-- TOC entry 214 (class 1259 OID 322464)
-- Name: directiontitlehist; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE directiontitlehist (
    idtitle bigint NOT NULL,
    date date,
    title character varying(255),
    iddirection bigint
);


--
-- TOC entry 185 (class 1259 OID 321915)
-- Name: etatmateriel; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE etatmateriel (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 186 (class 1259 OID 321918)
-- Name: financement; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE financement (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 187 (class 1259 OID 321921)
-- Name: fournisseur; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE fournisseur (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 188 (class 1259 OID 321924)
-- Name: fournisseurdetail; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE fournisseurdetail (
    idfourn bigint NOT NULL,
    adresse character varying(255),
    contact character varying(255),
    nif character varying(255),
    nomfourn character varying(255),
    observation character varying(255),
    stat character varying(255)
);


--
-- TOC entry 189 (class 1259 OID 321930)
-- Name: fournisseurdetail_idfourn_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE fournisseurdetail_idfourn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 189
-- Name: fournisseurdetail_idfourn_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE fournisseurdetail_idfourn_seq OWNED BY fournisseurdetail.idfourn;


--
-- TOC entry 190 (class 1259 OID 321932)
-- Name: hibernate_sequences; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE hibernate_sequences (
    sequence_name character varying(255),
    sequence_next_hi_value integer
);


--
-- TOC entry 191 (class 1259 OID 321935)
-- Name: localite; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE localite (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 192 (class 1259 OID 321938)
-- Name: ma; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE ma (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 193 (class 1259 OID 321941)
-- Name: marque; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE marque (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 194 (class 1259 OID 321944)
-- Name: materiel; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE materiel (
    typemateriels integer NOT NULL,
    idmateriel bigint NOT NULL,
    anneeacquisition character varying(255),
    autre character varying(255),
    code character varying(255),
    documentpath character varying(255),
    especeunite character varying(255),
    image bytea,
    numserie character varying(255),
    numerotype bigint,
    origine character varying(255),
    pu real,
    reference character varying(255),
    renseignement character varying(255),
    serienumero integer NOT NULL,
    validation boolean NOT NULL,
    montant_facture real,
    reffacture character varying(255),
    idbureau bigint,
    idcar integer,
    idcateg bigint,
    imdepositaire bigint,
    desingationid bigint,
    imdetenteur bigint,
    iddirection bigint,
    idetat bigint,
    idmarque bigint,
    opentreeid bigint,
    idnom bigint,
    idsevice bigint,
    idtypemateriel bigint,
    idfin bigint,
    idfourn bigint,
    idmodacq bigint,
    materielcount integer
);


--
-- TOC entry 195 (class 1259 OID 321950)
-- Name: motifdecharge; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE motifdecharge (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 196 (class 1259 OID 321953)
-- Name: motifsortie; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE motifsortie (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 197 (class 1259 OID 321956)
-- Name: nomenclature; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE nomenclature (
    id bigint NOT NULL,
    designation character varying(255),
    nomenclature character varying(255)
);


--
-- TOC entry 198 (class 1259 OID 321962)
-- Name: op_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE op_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 199 (class 1259 OID 321964)
-- Name: opattribution; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opattribution (
    id bigint NOT NULL,
    date date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    "time" time without time zone,
    iddirection bigint,
    idoperateur bigint,
    detenteureffectif character varying(255),
    numerodet character varying(255),
    iddetenteur bigint,
    idmat bigint
);


--
-- TOC entry 200 (class 1259 OID 321970)
-- Name: opdettachement; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opdettachement (
    id bigint NOT NULL,
    date date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    "time" time without time zone,
    iddirection bigint,
    idoperateur bigint,
    iddetenteur bigint,
    idmat bigint,
    idmotifdett bigint
);


--
-- TOC entry 201 (class 1259 OID 321976)
-- Name: opentreearticle; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opentreearticle (
    id bigint NOT NULL,
    date date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    "time" time without time zone,
    iddirection bigint,
    idoperateur bigint,
    idart bigint
);


--
-- TOC entry 202 (class 1259 OID 321982)
-- Name: opentreemateriel; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opentreemateriel (
    entreeid bigint NOT NULL,
    materielid bigint NOT NULL
);


--
-- TOC entry 203 (class 1259 OID 321985)
-- Name: operationentree; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE operationentree (
    id bigint NOT NULL,
    date date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    "time" time without time zone,
    iddirection bigint,
    idoperateur bigint,
    numoperation character varying(255),
    idmat bigint,
    pathdoc character varying(255),
    reffact character varying(255)
);


--
-- TOC entry 204 (class 1259 OID 321991)
-- Name: opsaisie; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opsaisie (
    id bigint NOT NULL,
    date date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    "time" time without time zone,
    iddirection bigint,
    idoperateur bigint,
    designationref character varying(255),
    idrefer bigint
);


--
-- TOC entry 205 (class 1259 OID 321997)
-- Name: opsortie; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opsortie (
    id bigint NOT NULL,
    date date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    "time" time without time zone,
    iddirection bigint,
    idoperateur bigint,
    numoperation character varying(255),
    idmat bigint,
    pj character varying(255),
    idbureau bigint,
    iddirect bigint,
    idmotif bigint,
    idserv bigint
);


--
-- TOC entry 206 (class 1259 OID 322003)
-- Name: opsortiearticle; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opsortiearticle (
    id bigint NOT NULL,
    date date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    "time" time without time zone,
    iddirection bigint,
    idoperateur bigint,
    decision character varying(255),
    nombretos bigint,
    idart bigint,
    idbenefi bigint
);


--
-- TOC entry 207 (class 1259 OID 322009)
-- Name: poste; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE poste (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 208 (class 1259 OID 322012)
-- Name: ref_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE ref_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 209 (class 1259 OID 322014)
-- Name: service; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE service (
    id bigint NOT NULL,
    designation character varying(255),
    codeservice character varying(255)
);


--
-- TOC entry 210 (class 1259 OID 322020)
-- Name: typemateriel; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE typemateriel (
    id bigint NOT NULL,
    designation character varying(255),
    codetypemate character varying(255),
    idnomenclature bigint
);


--
-- TOC entry 211 (class 1259 OID 322026)
-- Name: typeobjet; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE typeobjet (
    id bigint NOT NULL,
    designation character varying(255),
    caracteristique character varying(255)
);

--
-- TOC entry 213 (class 1259 OID 322038)
-- Name: useri_iduser_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE useri_iduser_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

--
-- TOC entry 212 (class 1259 OID 322032)
-- Name: useri; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE useri (
    iduser integer primary key,
    designation character varying(255),
    role character varying(255)
);





--
-- TOC entry 2362 (class 0 OID 0)
-- Dependencies: 213
-- Name: useri_iduser_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

--ALTER SEQUENCE useri_iduser_seq OWNED BY useri.iduser;


--
-- TOC entry 2040 (class 2604 OID 322040)
-- Name: idfourn; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY fournisseurdetail ALTER COLUMN idfourn SET DEFAULT nextval('fournisseurdetail_idfourn_seq'::regclass);


--
-- TOC entry 2041 (class 2604 OID 322041)
-- Name: iduser; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY useri ALTER COLUMN iduser SET DEFAULT nextval('useri_iduser_seq'::regclass);


--
--TRIGER TO INCREMENT SEQUENCE AUTO
--
--
-- TOC entry 2206 (class 0 OID 0)
-- Dependencies: 172
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('account_id_seq', 1, false);


--
-- TOC entry 2207 (class 0 OID 0)
-- Dependencies: 173
-- Name: account_idart_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('account_idart_seq', 1, false);


--
-- TOC entry 2155 (class 0 OID 323971)
-- Dependencies: 174
-- Data for Name: adresse; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2208 (class 0 OID 0)
-- Dependencies: 175
-- Name: ag_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('ag_seq', 1, false);
--
-- TOC entry 2209 (class 0 OID 0)
-- Dependencies: 182
-- Name: designation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('designation_id_seq', 1, false);
