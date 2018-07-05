--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.7
-- Dumped by pg_dump version 9.6.7

-- Started on 2018-07-05 11:56:50

SET statement_timeout = 0;
SET lock_timeout = 0;
--SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
--SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12387)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2483 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 224 (class 1259 OID 43938)
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 225 (class 1259 OID 43940)
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
-- TOC entry 186 (class 1259 OID 43348)
-- Name: adresse; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE adresse (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 221 (class 1259 OID 43932)
-- Name: ag_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE ag_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 187 (class 1259 OID 43353)
-- Name: agent; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE agent (
    idagent bigint NOT NULL,
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
-- TOC entry 188 (class 1259 OID 43363)
-- Name: article; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 189 (class 1259 OID 43371)
-- Name: bureau; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE bureau (
    id bigint NOT NULL,
    designation character varying(255),
    codebureau character varying(255)
);


--
-- TOC entry 190 (class 1259 OID 43381)
-- Name: categoriemat; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE categoriemat (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 191 (class 1259 OID 43386)
-- Name: codearticle; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE codearticle (
    id bigint NOT NULL,
    designation character varying(255),
    idtypeobj bigint
);


--
-- TOC entry 192 (class 1259 OID 43391)
-- Name: designation; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 226 (class 1259 OID 43942)
-- Name: designation_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE designation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 193 (class 1259 OID 43399)
-- Name: devise; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE devise (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 194 (class 1259 OID 43404)
-- Name: direction; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 195 (class 1259 OID 43414)
-- Name: etatmateriel; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE etatmateriel (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 196 (class 1259 OID 43419)
-- Name: financement; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE financement (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 197 (class 1259 OID 43424)
-- Name: fournisseur; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE fournisseur (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 199 (class 1259 OID 43431)
-- Name: fournisseurdetail; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 198 (class 1259 OID 43429)
-- Name: fournisseurdetail_idfourn_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE fournisseurdetail_idfourn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2484 (class 0 OID 0)
-- Dependencies: 198
-- Name: fournisseurdetail_idfourn_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE fournisseurdetail_idfourn_seq OWNED BY fournisseurdetail.idfourn;


--
-- TOC entry 185 (class 1259 OID 34552)
-- Name: hibernate_sequences; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE hibernate_sequences (
    sequence_name character varying(255),
    sequence_next_hi_value integer
);


--
-- TOC entry 200 (class 1259 OID 43440)
-- Name: localite; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE localite (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 201 (class 1259 OID 43445)
-- Name: ma; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE ma (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 202 (class 1259 OID 43450)
-- Name: marque; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE marque (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 203 (class 1259 OID 43455)
-- Name: materiel; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 204 (class 1259 OID 43465)
-- Name: motifdecharge; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE motifdecharge (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 205 (class 1259 OID 43470)
-- Name: motifsortie; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE motifsortie (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 206 (class 1259 OID 43475)
-- Name: nomenclature; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE nomenclature (
    id bigint NOT NULL,
    designation character varying(255),
    nomenclature character varying(255)
);


--
-- TOC entry 222 (class 1259 OID 43934)
-- Name: op_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE op_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 207 (class 1259 OID 43485)
-- Name: opattribution; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 208 (class 1259 OID 43493)
-- Name: opdettachement; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 209 (class 1259 OID 43501)
-- Name: opentreearticle; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 219 (class 1259 OID 43579)
-- Name: opentreemateriel; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE opentreemateriel (
    entreeid bigint NOT NULL,
    materielid bigint NOT NULL
);


--
-- TOC entry 220 (class 1259 OID 43584)
-- Name: operationentree; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 210 (class 1259 OID 43509)
-- Name: opsaisie; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 211 (class 1259 OID 43517)
-- Name: opsortie; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 212 (class 1259 OID 43525)
-- Name: opsortiearticle; Type: TABLE; Schema: public; Owner: -
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
-- TOC entry 213 (class 1259 OID 43533)
-- Name: poste; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE poste (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 223 (class 1259 OID 43936)
-- Name: ref_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE ref_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 214 (class 1259 OID 43538)
-- Name: service; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE service (
    id bigint NOT NULL,
    designation character varying(255),
    codeservice character varying(255)
);


--
-- TOC entry 215 (class 1259 OID 43548)
-- Name: typemateriel; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE typemateriel (
    id bigint NOT NULL,
    designation character varying(255),
    codetypemate character varying(255),
    idnomenclature bigint
);


--
-- TOC entry 216 (class 1259 OID 43558)
-- Name: typeobjet; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE typeobjet (
    id bigint NOT NULL,
    designation character varying(255),
    caracteristique character varying(255)
);


--
-- TOC entry 218 (class 1259 OID 43568)
-- Name: useri; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE useri (
    iduser integer NOT NULL,
    designation character varying(255),
    role character varying(255)
);


--
-- TOC entry 217 (class 1259 OID 43566)
-- Name: useri_iduser_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE useri_iduser_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2485 (class 0 OID 0)
-- Dependencies: 217
-- Name: useri_iduser_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE useri_iduser_seq OWNED BY useri.iduser;


--
-- TOC entry 2166 (class 2604 OID 43434)
-- Name: fournisseurdetail idfourn; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY fournisseurdetail ALTER COLUMN idfourn SET DEFAULT nextval('fournisseurdetail_idfourn_seq'::regclass);


--
-- TOC entry 2167 (class 2604 OID 43571)
-- Name: useri iduser; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY useri ALTER COLUMN iduser SET DEFAULT nextval('useri_iduser_seq'::regclass);


--
-- TOC entry 2486 (class 0 OID 0)
-- Dependencies: 224
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('account_id_seq', 1, false);


--
-- TOC entry 2487 (class 0 OID 0)
-- Dependencies: 225
-- Name: account_idart_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('account_idart_seq', 1, false);


--
-- TOC entry 2436 (class 0 OID 43348)
-- Dependencies: 186
-- Data for Name: adresse; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2488 (class 0 OID 0)
-- Dependencies: 221
-- Name: ag_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('ag_seq', 1, true);


--
-- TOC entry 2437 (class 0 OID 43353)
-- Dependencies: 187
-- Data for Name: agent; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO agent VALUES (1, true, 0, 'Administrateur', '$2a$10$R0N4ucELBAZY0/6PT668Qea6kT9kku0gyBW9cUv2hkB/57kCxCDYi', NULL, NULL, NULL, NULL, 1, NULL);


--
-- TOC entry 2438 (class 0 OID 43363)
-- Dependencies: 188
-- Data for Name: article; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2439 (class 0 OID 43371)
-- Dependencies: 189
-- Data for Name: bureau; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2440 (class 0 OID 43381)
-- Dependencies: 190
-- Data for Name: categoriemat; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2441 (class 0 OID 43386)
-- Dependencies: 191
-- Data for Name: codearticle; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2442 (class 0 OID 43391)
-- Dependencies: 192
-- Data for Name: designation; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2489 (class 0 OID 0)
-- Dependencies: 226
-- Name: designation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('designation_id_seq', 1, false);


--
-- TOC entry 2443 (class 0 OID 43399)
-- Dependencies: 193
-- Data for Name: devise; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO devise VALUES (7, 'Tanindrazana - Fahafahana - Fandrosoana');


--
-- TOC entry 2444 (class 0 OID 43404)
-- Dependencies: 194
-- Data for Name: direction; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO direction VALUES (1, 'Service de l''inforamtique', 'Budget DSI', 'DSI', '4 DSI', '3 DSI');
INSERT INTO direction VALUES (2, 'Service des Equipements', 'Budget DSE', 'DSE', '4 DSE', '3 DSE');
INSERT INTO direction VALUES (3, 'Direction des Ressources et de la Formation', 'Budget DRF', 'DRF', '4 DRF', '3 DRF');
INSERT INTO direction VALUES (4, 'Direction GÃ©nÃ©rale des Douanes', 'Budget DGD', 'DGD', '4 DGD', '3 DGD');


--
-- TOC entry 2445 (class 0 OID 43414)
-- Dependencies: 195
-- Data for Name: etatmateriel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO etatmateriel VALUES (26, 'Nouveau');
INSERT INTO etatmateriel VALUES (27, 'Mauvais');


--
-- TOC entry 2446 (class 0 OID 43419)
-- Dependencies: 196
-- Data for Name: financement; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2447 (class 0 OID 43424)
-- Dependencies: 197
-- Data for Name: fournisseur; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2449 (class 0 OID 43431)
-- Dependencies: 199
-- Data for Name: fournisseurdetail; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2490 (class 0 OID 0)
-- Dependencies: 198
-- Name: fournisseurdetail_idfourn_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('fournisseurdetail_idfourn_seq', 1, false);


--
-- TOC entry 2435 (class 0 OID 34552)
-- Dependencies: 185
-- Data for Name: hibernate_sequences; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO hibernate_sequences VALUES ('Referentiel', 2);
INSERT INTO hibernate_sequences VALUES ('Operation', 6);


--
-- TOC entry 2450 (class 0 OID 43440)
-- Dependencies: 200
-- Data for Name: localite; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2451 (class 0 OID 43445)
-- Dependencies: 201
-- Data for Name: ma; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO ma VALUES (33, 'Renouvellement de materiel');
INSERT INTO ma VALUES (32, 'Achat');


--
-- TOC entry 2452 (class 0 OID 43450)
-- Dependencies: 202
-- Data for Name: marque; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO marque VALUES (28, 'Peugeot');
INSERT INTO marque VALUES (29, 'Prolink');
INSERT INTO marque VALUES (30, 'Raffles');
INSERT INTO marque VALUES (31, 'Inconnue');


--
-- TOC entry 2453 (class 0 OID 43455)
-- Dependencies: 203
-- Data for Name: materiel; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2454 (class 0 OID 43465)
-- Dependencies: 204
-- Data for Name: motifdecharge; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO motifdecharge VALUES (34, 'Affectation');
INSERT INTO motifdecharge VALUES (35, 'Perte');


--
-- TOC entry 2455 (class 0 OID 43470)
-- Dependencies: 205
-- Data for Name: motifsortie; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO motifsortie VALUES (5, 'Affectation');
INSERT INTO motifsortie VALUES (6, 'Perte');


--
-- TOC entry 2456 (class 0 OID 43475)
-- Dependencies: 206
-- Data for Name: nomenclature; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO nomenclature VALUES (9, 'Combustible et Luminaires', '1');
INSERT INTO nomenclature VALUES (10, 'Effets d''habillement, Ã©quipement, campement, harnachement, pansage', '2');
INSERT INTO nomenclature VALUES (11, 'Meubles et objets d''ameublement, literie et couchage', '3');
INSERT INTO nomenclature VALUES (12, 'Outillage, instrument et appareils divers, matÃ©riel de transport, instruments de musique, matÃ©riel sportif, matÃ©riel de guerre', '5');
INSERT INTO nomenclature VALUES (13, 'Ouvrages de bibliothÃ©ques, de sciences, et arts, MatÃ©riel d'' enseignement. Fournitures diverses', '10');


--
-- TOC entry 2491 (class 0 OID 0)
-- Dependencies: 222
-- Name: op_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('op_seq', 36, true);


--
-- TOC entry 2457 (class 0 OID 43485)
-- Dependencies: 207
-- Data for Name: opattribution; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2458 (class 0 OID 43493)
-- Dependencies: 208
-- Data for Name: opdettachement; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2459 (class 0 OID 43501)
-- Dependencies: 209
-- Data for Name: opentreearticle; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2469 (class 0 OID 43579)
-- Dependencies: 219
-- Data for Name: opentreemateriel; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2470 (class 0 OID 43584)
-- Dependencies: 220
-- Data for Name: operationentree; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2460 (class 0 OID 43509)
-- Dependencies: 210
-- Data for Name: opsaisie; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO opsaisie VALUES (1, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:18:19.097', NULL, 1, 'Direction', 1);
INSERT INTO opsaisie VALUES (2, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:19:11.371', NULL, 1, 'Direction', 2);
INSERT INTO opsaisie VALUES (3, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:20:07.564', NULL, 1, 'Direction', 3);
INSERT INTO opsaisie VALUES (4, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:21:03.729', NULL, 1, 'Direction', 4);
INSERT INTO opsaisie VALUES (5, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:21:20.174', NULL, 1, 'Motif de Sortie', 5);
INSERT INTO opsaisie VALUES (6, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:21:27.555', NULL, 1, 'Motif de Sortie', 6);
INSERT INTO opsaisie VALUES (7, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:22:29.684', NULL, 1, NULL, 7);
INSERT INTO opsaisie VALUES (8, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:22:49.513', NULL, 1, 'Poste', 8);
INSERT INTO opsaisie VALUES (9, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:32:09.664', NULL, 1, 'Nomenclature', 9);
INSERT INTO opsaisie VALUES (10, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:32:47.594', NULL, 1, 'Nomenclature', 10);
INSERT INTO opsaisie VALUES (11, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:33:22.089', NULL, 1, 'Nomenclature', 11);
INSERT INTO opsaisie VALUES (12, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:34:59.848', NULL, 1, 'Nomenclature', 12);
INSERT INTO opsaisie VALUES (13, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:36:17.416', NULL, 1, 'Nomenclature', 13);
INSERT INTO opsaisie VALUES (14, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:37:49.668', NULL, 1, NULL, 14);
INSERT INTO opsaisie VALUES (15, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:38:29.801', NULL, 1, NULL, 15);
INSERT INTO opsaisie VALUES (16, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:38:54.599', NULL, 1, NULL, 16);
INSERT INTO opsaisie VALUES (17, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:39:10.343', NULL, 1, NULL, 17);
INSERT INTO opsaisie VALUES (18, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:39:49.696', NULL, 1, NULL, 18);
INSERT INTO opsaisie VALUES (19, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:40:09.569', NULL, 1, NULL, 19);
INSERT INTO opsaisie VALUES (20, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:40:36.469', NULL, 1, NULL, 20);
INSERT INTO opsaisie VALUES (21, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:41:16.338', NULL, 1, NULL, 21);
INSERT INTO opsaisie VALUES (22, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:41:40.113', NULL, 1, NULL, 22);
INSERT INTO opsaisie VALUES (23, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:42:23.455', NULL, 1, NULL, 23);
INSERT INTO opsaisie VALUES (24, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:44:06.874', NULL, 1, NULL, 24);
INSERT INTO opsaisie VALUES (25, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:44:20.469', NULL, 1, NULL, 25);
INSERT INTO opsaisie VALUES (26, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:45:19.927', NULL, 1, 'Etat du matériel', 26);
INSERT INTO opsaisie VALUES (27, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:45:31.4', NULL, 1, 'Etat du matériel', 27);
INSERT INTO opsaisie VALUES (28, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:49:59.045', NULL, 1, 'Marque', 28);
INSERT INTO opsaisie VALUES (29, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:50:10.64', NULL, 1, 'Marque', 29);
INSERT INTO opsaisie VALUES (30, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:50:21.713', NULL, 1, 'Marque', 30);
INSERT INTO opsaisie VALUES (31, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:50:34.212', NULL, 1, 'Marque', 31);
INSERT INTO opsaisie VALUES (32, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:51:31.491', NULL, 1, 'Mode d''Acquisition', 32);
INSERT INTO opsaisie VALUES (33, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:52:16.31', NULL, 1, 'Mode d''Acquisition', 33);
INSERT INTO opsaisie VALUES (34, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:52:32.236', NULL, 1, 'Mode d''Acquisition', 32);
INSERT INTO opsaisie VALUES (35, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:52:55.675', NULL, 1, 'Motif du décharge', 34);
INSERT INTO opsaisie VALUES (36, '2018-07-05', NULL, '172.16.0.55', 'ACCEPTED', '11:53:03.186', NULL, 1, 'Motif du décharge', 35);


--
-- TOC entry 2461 (class 0 OID 43517)
-- Dependencies: 211
-- Data for Name: opsortie; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2462 (class 0 OID 43525)
-- Dependencies: 212
-- Data for Name: opsortiearticle; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2463 (class 0 OID 43533)
-- Dependencies: 213
-- Data for Name: poste; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO poste VALUES (8, 'Admin');


--
-- TOC entry 2492 (class 0 OID 0)
-- Dependencies: 223
-- Name: ref_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('ref_seq', 35, true);


--
-- TOC entry 2464 (class 0 OID 43538)
-- Dependencies: 214
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2465 (class 0 OID 43548)
-- Dependencies: 215
-- Data for Name: typemateriel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typemateriel VALUES (14, 'Lampe tube fluorescent', 'LTF', 9);
INSERT INTO typemateriel VALUES (15, 'RÃ©glettes LED', 'RL', 9);
INSERT INTO typemateriel VALUES (16, 'Tenue de travail', 'TDT', 10);
INSERT INTO typemateriel VALUES (17, 'Tenue de cÃ©rÃ©monie', 'TDC', 10);
INSERT INTO typemateriel VALUES (18, 'Armoire', 'ARM', 11);
INSERT INTO typemateriel VALUES (19, 'BibliothÃ¨que', 'BIB', 11);
INSERT INTO typemateriel VALUES (20, 'Aspirateur', 'ASP', 11);
INSERT INTO typemateriel VALUES (21, 'Ordinateur', 'ORD', 12);
INSERT INTO typemateriel VALUES (22, 'Imprimante', 'IMP', 12);
INSERT INTO typemateriel VALUES (23, 'VÃ©hicules', 'VEH', 12);
INSERT INTO typemateriel VALUES (24, 'imprimÃ©s', 'IPM', 13);
INSERT INTO typemateriel VALUES (25, 'Registre', 'REG', 13);


--
-- TOC entry 2466 (class 0 OID 43558)
-- Dependencies: 216
-- Data for Name: typeobjet; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2468 (class 0 OID 43568)
-- Dependencies: 218
-- Data for Name: useri; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO useri VALUES (1, 'administrateur', 'ROLE_ADMIN');
INSERT INTO useri VALUES (2, 'DÃ©positaires comptables', 'ROLE_DC');
INSERT INTO useri VALUES (3, 'Gestionaire d''Activites', 'ROLE_GAC');
INSERT INTO useri VALUES (4, 'SISE', 'ROLE_SISE');
INSERT INTO useri VALUES (5, 'Agent', 'ROLE_AGENT');


--
-- TOC entry 2493 (class 0 OID 0)
-- Dependencies: 217
-- Name: useri_iduser_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('useri_iduser_seq', 5, true);


--
-- TOC entry 2169 (class 2606 OID 43352)
-- Name: adresse adresse_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY adresse
    ADD CONSTRAINT adresse_pkey PRIMARY KEY (id);


--
-- TOC entry 2171 (class 2606 OID 43362)
-- Name: agent agent_im_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT agent_im_key UNIQUE (im);


--
-- TOC entry 2173 (class 2606 OID 43360)
-- Name: agent agent_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT agent_pkey PRIMARY KEY (idagent);


--
-- TOC entry 2175 (class 2606 OID 43370)
-- Name: article article_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT article_pkey PRIMARY KEY (idarticle);


--
-- TOC entry 2177 (class 2606 OID 43380)
-- Name: bureau bureau_codebureau_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY bureau
    ADD CONSTRAINT bureau_codebureau_key UNIQUE (codebureau);


--
-- TOC entry 2179 (class 2606 OID 43378)
-- Name: bureau bureau_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY bureau
    ADD CONSTRAINT bureau_pkey PRIMARY KEY (id);


--
-- TOC entry 2181 (class 2606 OID 43385)
-- Name: categoriemat categoriemat_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY categoriemat
    ADD CONSTRAINT categoriemat_pkey PRIMARY KEY (id);


--
-- TOC entry 2183 (class 2606 OID 43390)
-- Name: codearticle codearticle_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY codearticle
    ADD CONSTRAINT codearticle_pkey PRIMARY KEY (id);


--
-- TOC entry 2185 (class 2606 OID 43398)
-- Name: designation designation_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT designation_pkey PRIMARY KEY (iddesignation);


--
-- TOC entry 2187 (class 2606 OID 43403)
-- Name: devise devise_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY devise
    ADD CONSTRAINT devise_pkey PRIMARY KEY (id);


--
-- TOC entry 2189 (class 2606 OID 43413)
-- Name: direction direction_codedirection_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY direction
    ADD CONSTRAINT direction_codedirection_key UNIQUE (codedirection);


--
-- TOC entry 2191 (class 2606 OID 43411)
-- Name: direction direction_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY direction
    ADD CONSTRAINT direction_pkey PRIMARY KEY (id);


--
-- TOC entry 2193 (class 2606 OID 43418)
-- Name: etatmateriel etatmateriel_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY etatmateriel
    ADD CONSTRAINT etatmateriel_pkey PRIMARY KEY (id);


--
-- TOC entry 2195 (class 2606 OID 43423)
-- Name: financement financement_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY financement
    ADD CONSTRAINT financement_pkey PRIMARY KEY (id);


--
-- TOC entry 2197 (class 2606 OID 43428)
-- Name: fournisseur fournisseur_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY fournisseur
    ADD CONSTRAINT fournisseur_pkey PRIMARY KEY (id);


--
-- TOC entry 2199 (class 2606 OID 43439)
-- Name: fournisseurdetail fournisseurdetail_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY fournisseurdetail
    ADD CONSTRAINT fournisseurdetail_pkey PRIMARY KEY (idfourn);


--
-- TOC entry 2201 (class 2606 OID 43444)
-- Name: localite localite_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY localite
    ADD CONSTRAINT localite_pkey PRIMARY KEY (id);


--
-- TOC entry 2203 (class 2606 OID 43449)
-- Name: ma ma_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY ma
    ADD CONSTRAINT ma_pkey PRIMARY KEY (id);


--
-- TOC entry 2205 (class 2606 OID 43454)
-- Name: marque marque_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY marque
    ADD CONSTRAINT marque_pkey PRIMARY KEY (id);


--
-- TOC entry 2207 (class 2606 OID 43464)
-- Name: materiel materiel_code_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT materiel_code_key UNIQUE (code);


--
-- TOC entry 2209 (class 2606 OID 43462)
-- Name: materiel materiel_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT materiel_pkey PRIMARY KEY (idmateriel);


--
-- TOC entry 2211 (class 2606 OID 43469)
-- Name: motifdecharge motifdecharge_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY motifdecharge
    ADD CONSTRAINT motifdecharge_pkey PRIMARY KEY (id);


--
-- TOC entry 2213 (class 2606 OID 43474)
-- Name: motifsortie motifsortie_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY motifsortie
    ADD CONSTRAINT motifsortie_pkey PRIMARY KEY (id);


--
-- TOC entry 2215 (class 2606 OID 43484)
-- Name: nomenclature nomenclature_nomenclature_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY nomenclature
    ADD CONSTRAINT nomenclature_nomenclature_key UNIQUE (nomenclature);


--
-- TOC entry 2217 (class 2606 OID 43482)
-- Name: nomenclature nomenclature_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY nomenclature
    ADD CONSTRAINT nomenclature_pkey PRIMARY KEY (id);


--
-- TOC entry 2219 (class 2606 OID 43492)
-- Name: opattribution opattribution_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opattribution
    ADD CONSTRAINT opattribution_pkey PRIMARY KEY (id);


--
-- TOC entry 2221 (class 2606 OID 43500)
-- Name: opdettachement opdettachement_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT opdettachement_pkey PRIMARY KEY (id);


--
-- TOC entry 2223 (class 2606 OID 43508)
-- Name: opentreearticle opentreearticle_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreearticle
    ADD CONSTRAINT opentreearticle_pkey PRIMARY KEY (id);


--
-- TOC entry 2247 (class 2606 OID 43583)
-- Name: opentreemateriel opentreemateriel_materielid_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreemateriel
    ADD CONSTRAINT opentreemateriel_materielid_key UNIQUE (materielid);


--
-- TOC entry 2249 (class 2606 OID 43591)
-- Name: operationentree operationentree_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY operationentree
    ADD CONSTRAINT operationentree_pkey PRIMARY KEY (id);


--
-- TOC entry 2225 (class 2606 OID 43516)
-- Name: opsaisie opsaisie_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsaisie
    ADD CONSTRAINT opsaisie_pkey PRIMARY KEY (id);


--
-- TOC entry 2227 (class 2606 OID 43524)
-- Name: opsortie opsortie_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT opsortie_pkey PRIMARY KEY (id);


--
-- TOC entry 2229 (class 2606 OID 43532)
-- Name: opsortiearticle opsortiearticle_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortiearticle
    ADD CONSTRAINT opsortiearticle_pkey PRIMARY KEY (id);


--
-- TOC entry 2231 (class 2606 OID 43537)
-- Name: poste poste_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY poste
    ADD CONSTRAINT poste_pkey PRIMARY KEY (id);


--
-- TOC entry 2233 (class 2606 OID 43547)
-- Name: service service_codeservice_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY service
    ADD CONSTRAINT service_codeservice_key UNIQUE (codeservice);


--
-- TOC entry 2235 (class 2606 OID 43545)
-- Name: service service_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY service
    ADD CONSTRAINT service_pkey PRIMARY KEY (id);


--
-- TOC entry 2237 (class 2606 OID 43557)
-- Name: typemateriel typemateriel_codetypemate_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typemateriel
    ADD CONSTRAINT typemateriel_codetypemate_key UNIQUE (codetypemate);


--
-- TOC entry 2239 (class 2606 OID 43555)
-- Name: typemateriel typemateriel_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typemateriel
    ADD CONSTRAINT typemateriel_pkey PRIMARY KEY (id);


--
-- TOC entry 2241 (class 2606 OID 43565)
-- Name: typeobjet typeobjet_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typeobjet
    ADD CONSTRAINT typeobjet_pkey PRIMARY KEY (id);


--
-- TOC entry 2243 (class 2606 OID 43576)
-- Name: useri useri_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY useri
    ADD CONSTRAINT useri_pkey PRIMARY KEY (iduser);


--
-- TOC entry 2245 (class 2606 OID 43578)
-- Name: useri useri_role_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY useri
    ADD CONSTRAINT useri_role_key UNIQUE (role);


--
-- TOC entry 2271 (class 2606 OID 43697)
-- Name: materiel fk15adc9c32e46c86e; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c32e46c86e FOREIGN KEY (idetat) REFERENCES etatmateriel(id);


--
-- TOC entry 2272 (class 2606 OID 43702)
-- Name: materiel fk15adc9c330e29cfc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c330e29cfc FOREIGN KEY (idfin) REFERENCES financement(id);


--
-- TOC entry 2284 (class 2606 OID 43762)
-- Name: materiel fk15adc9c33379fcf7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c33379fcf7 FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2275 (class 2606 OID 43717)
-- Name: materiel fk15adc9c346613e22; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c346613e22 FOREIGN KEY (idfourn) REFERENCES fournisseur(id);


--
-- TOC entry 2283 (class 2606 OID 43757)
-- Name: materiel fk15adc9c34fab860e; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c34fab860e FOREIGN KEY (imdepositaire) REFERENCES agent(idagent);


--
-- TOC entry 2286 (class 2606 OID 43772)
-- Name: materiel fk15adc9c35832859b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c35832859b FOREIGN KEY (idbureau) REFERENCES bureau(id);


--
-- TOC entry 2273 (class 2606 OID 43707)
-- Name: materiel fk15adc9c35b8b2e14; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c35b8b2e14 FOREIGN KEY (idnom) REFERENCES nomenclature(id);


--
-- TOC entry 2280 (class 2606 OID 43742)
-- Name: materiel fk15adc9c3778afea2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c3778afea2 FOREIGN KEY (idcar) REFERENCES typemateriel(id);


--
-- TOC entry 2274 (class 2606 OID 43712)
-- Name: materiel fk15adc9c37b89d5ed; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c37b89d5ed FOREIGN KEY (idmarque) REFERENCES marque(id);


--
-- TOC entry 2282 (class 2606 OID 43752)
-- Name: materiel fk15adc9c37dbd55d7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c37dbd55d7 FOREIGN KEY (opentreeid) REFERENCES operationentree(id);


--
-- TOC entry 2276 (class 2606 OID 43722)
-- Name: materiel fk15adc9c38e46bd41; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c38e46bd41 FOREIGN KEY (idtypemateriel) REFERENCES typemateriel(id);


--
-- TOC entry 2277 (class 2606 OID 43727)
-- Name: materiel fk15adc9c395ec89eb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c395ec89eb FOREIGN KEY (desingationid) REFERENCES designation(iddesignation);


--
-- TOC entry 2279 (class 2606 OID 43737)
-- Name: materiel fk15adc9c39b6f6e32; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c39b6f6e32 FOREIGN KEY (idcateg) REFERENCES categoriemat(id);


--
-- TOC entry 2278 (class 2606 OID 43732)
-- Name: materiel fk15adc9c3b32a2ceb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c3b32a2ceb FOREIGN KEY (idsevice) REFERENCES service(id);


--
-- TOC entry 2285 (class 2606 OID 43767)
-- Name: materiel fk15adc9c3d84ea6c6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c3d84ea6c6 FOREIGN KEY (idmodacq) REFERENCES ma(id);


--
-- TOC entry 2281 (class 2606 OID 43747)
-- Name: materiel fk15adc9c3ebd363df; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk15adc9c3ebd363df FOREIGN KEY (imdetenteur) REFERENCES agent(idagent);


--
-- TOC entry 2297 (class 2606 OID 43827)
-- Name: opentreearticle fk162112ae7e76a12; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreearticle
    ADD CONSTRAINT fk162112ae7e76a12 FOREIGN KEY (idart) REFERENCES article(idarticle);


--
-- TOC entry 2263 (class 2606 OID 43657)
-- Name: codearticle fk1d64a2976f5e382; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY codearticle
    ADD CONSTRAINT fk1d64a2976f5e382 FOREIGN KEY (idtypeobj) REFERENCES typeobjet(id);


--
-- TOC entry 2264 (class 2606 OID 43662)
-- Name: designation fk2db687d730e29cfc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk2db687d730e29cfc FOREIGN KEY (idfin) REFERENCES financement(id);


--
-- TOC entry 2266 (class 2606 OID 43672)
-- Name: designation fk2db687d746613e22; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk2db687d746613e22 FOREIGN KEY (idfourn) REFERENCES fournisseur(id);


--
-- TOC entry 2265 (class 2606 OID 43667)
-- Name: designation fk2db687d75b8b2e14; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk2db687d75b8b2e14 FOREIGN KEY (idnom) REFERENCES nomenclature(id);


--
-- TOC entry 2270 (class 2606 OID 43692)
-- Name: designation fk2db687d7778afea2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk2db687d7778afea2 FOREIGN KEY (idcar) REFERENCES typemateriel(id);


--
-- TOC entry 2267 (class 2606 OID 43677)
-- Name: designation fk2db687d77b89d5ed; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk2db687d77b89d5ed FOREIGN KEY (idmarque) REFERENCES marque(id);


--
-- TOC entry 2268 (class 2606 OID 43682)
-- Name: designation fk2db687d78e46bd41; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk2db687d78e46bd41 FOREIGN KEY (idtypemateriel) REFERENCES typemateriel(id);


--
-- TOC entry 2269 (class 2606 OID 43687)
-- Name: designation fk2db687d7d84ea6c6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk2db687d7d84ea6c6 FOREIGN KEY (idmodacq) REFERENCES ma(id);


--
-- TOC entry 2257 (class 2606 OID 43627)
-- Name: article fk379164d630e29cfc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk379164d630e29cfc FOREIGN KEY (idfin) REFERENCES financement(id);


--
-- TOC entry 2258 (class 2606 OID 43632)
-- Name: article fk379164d64fab860e; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk379164d64fab860e FOREIGN KEY (imdepositaire) REFERENCES agent(idagent);


--
-- TOC entry 2255 (class 2606 OID 43617)
-- Name: article fk379164d662d5245b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk379164d662d5245b FOREIGN KEY (imbeneficiaire) REFERENCES agent(idagent);


--
-- TOC entry 2261 (class 2606 OID 43647)
-- Name: article fk379164d671c7a8fb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk379164d671c7a8fb FOREIGN KEY (idfournisseur) REFERENCES fournisseur(id);


--
-- TOC entry 2256 (class 2606 OID 43622)
-- Name: article fk379164d6819d6785; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk379164d6819d6785 FOREIGN KEY (idcode) REFERENCES codearticle(id);


--
-- TOC entry 2259 (class 2606 OID 43637)
-- Name: article fk379164d6c89f8494; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk379164d6c89f8494 FOREIGN KEY (idmarqueart) REFERENCES marque(id);


--
-- TOC entry 2260 (class 2606 OID 43642)
-- Name: article fk379164d6d21921d9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk379164d6d21921d9 FOREIGN KEY (idmodacqart) REFERENCES ma(id);


--
-- TOC entry 2262 (class 2606 OID 43652)
-- Name: article fk379164d6f64c7f52; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk379164d6f64c7f52 FOREIGN KEY (iddirectionart) REFERENCES direction(id);


--
-- TOC entry 2308 (class 2606 OID 43882)
-- Name: opsortiearticle fk38d84bdb37867211; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortiearticle
    ADD CONSTRAINT fk38d84bdb37867211 FOREIGN KEY (idbenefi) REFERENCES agent(idagent);


--
-- TOC entry 2310 (class 2606 OID 43892)
-- Name: opsortiearticle fk38d84bdb7e76a12; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortiearticle
    ADD CONSTRAINT fk38d84bdb7e76a12 FOREIGN KEY (idart) REFERENCES article(idarticle);


--
-- TOC entry 2253 (class 2606 OID 43607)
-- Name: agent fk3c452e53379fcf7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT fk3c452e53379fcf7 FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2254 (class 2606 OID 43612)
-- Name: agent fk3c452e55832859b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT fk3c452e55832859b FOREIGN KEY (idbureau) REFERENCES bureau(id);


--
-- TOC entry 2251 (class 2606 OID 43597)
-- Name: agent fk3c452e57e26a5a3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT fk3c452e57e26a5a3 FOREIGN KEY (idservice) REFERENCES service(id);


--
-- TOC entry 2250 (class 2606 OID 43592)
-- Name: agent fk3c452e592cb1d4e; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT fk3c452e592cb1d4e FOREIGN KEY (idposteny) REFERENCES poste(id);


--
-- TOC entry 2252 (class 2606 OID 43602)
-- Name: agent fk3c452e5ff40fcc3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT fk3c452e5ff40fcc3 FOREIGN KEY (idrole) REFERENCES useri(iduser);


--
-- TOC entry 2295 (class 2606 OID 43817)
-- Name: opdettachement fk3f0d58df553e69c8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT fk3f0d58df553e69c8 FOREIGN KEY (iddetenteur) REFERENCES agent(idagent);


--
-- TOC entry 2293 (class 2606 OID 43807)
-- Name: opdettachement fk3f0d58df76fc22d; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT fk3f0d58df76fc22d FOREIGN KEY (idmotifdett) REFERENCES motifdecharge(id);


--
-- TOC entry 2291 (class 2606 OID 43797)
-- Name: opdettachement fk3f0d58df9d3350d4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT fk3f0d58df9d3350d4 FOREIGN KEY (idmat) REFERENCES materiel(idmateriel);


--
-- TOC entry 2290 (class 2606 OID 43792)
-- Name: opattribution fk491893be553e69c8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opattribution
    ADD CONSTRAINT fk491893be553e69c8 FOREIGN KEY (iddetenteur) REFERENCES agent(idagent);


--
-- TOC entry 2287 (class 2606 OID 43777)
-- Name: opattribution fk491893be9d3350d4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opattribution
    ADD CONSTRAINT fk491893be9d3350d4 FOREIGN KEY (idmat) REFERENCES materiel(idmateriel);


--
-- TOC entry 2312 (class 2606 OID 43902)
-- Name: typemateriel fk5b31d31d5a212a55; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typemateriel
    ADD CONSTRAINT fk5b31d31d5a212a55 FOREIGN KEY (idnomenclature) REFERENCES nomenclature(id);


--
-- TOC entry 2315 (class 2606 OID 43917)
-- Name: operationentree fk6b24c8359d3350d4b226efee; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY operationentree
    ADD CONSTRAINT fk6b24c8359d3350d4b226efee FOREIGN KEY (idmat) REFERENCES materiel(idmateriel);


--
-- TOC entry 2303 (class 2606 OID 43857)
-- Name: opsortie fk6b24c8359d3350d4c72ac49b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fk6b24c8359d3350d4c72ac49b FOREIGN KEY (idmat) REFERENCES materiel(idmateriel);


--
-- TOC entry 2313 (class 2606 OID 43907)
-- Name: opentreemateriel fk7708c6ab81cd286d; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreemateriel
    ADD CONSTRAINT fk7708c6ab81cd286d FOREIGN KEY (materielid) REFERENCES materiel(idmateriel);


--
-- TOC entry 2314 (class 2606 OID 43912)
-- Name: opentreemateriel fk7708c6abb3f4b8f6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreemateriel
    ADD CONSTRAINT fk7708c6abb3f4b8f6 FOREIGN KEY (entreeid) REFERENCES operationentree(id);


--
-- TOC entry 2302 (class 2606 OID 43852)
-- Name: opsortie fkc72ac49b50e0aaf1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fkc72ac49b50e0aaf1 FOREIGN KEY (idmotif) REFERENCES motifsortie(id);


--
-- TOC entry 2306 (class 2606 OID 43872)
-- Name: opsortie fkc72ac49b5832859b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fkc72ac49b5832859b FOREIGN KEY (idbureau) REFERENCES bureau(id);


--
-- TOC entry 2301 (class 2606 OID 43847)
-- Name: opsortie fkc72ac49b5cbfa81a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fkc72ac49b5cbfa81a FOREIGN KEY (idserv) REFERENCES service(id);


--
-- TOC entry 2304 (class 2606 OID 43862)
-- Name: opsortie fkc72ac49be4489cf7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fkc72ac49be4489cf7 FOREIGN KEY (iddirect) REFERENCES direction(id);


--
-- TOC entry 2298 (class 2606 OID 43832)
-- Name: opentreearticle fkda8cf54726d6721f162112ae; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreearticle
    ADD CONSTRAINT fkda8cf54726d6721f162112ae FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2311 (class 2606 OID 43897)
-- Name: opsortiearticle fkda8cf54726d6721f38d84bdb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortiearticle
    ADD CONSTRAINT fkda8cf54726d6721f38d84bdb FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2294 (class 2606 OID 43812)
-- Name: opdettachement fkda8cf54726d6721f3f0d58df; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT fkda8cf54726d6721f3f0d58df FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2289 (class 2606 OID 43787)
-- Name: opattribution fkda8cf54726d6721f491893be; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opattribution
    ADD CONSTRAINT fkda8cf54726d6721f491893be FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2317 (class 2606 OID 43927)
-- Name: operationentree fkda8cf54726d6721f6b24c835b226efee; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY operationentree
    ADD CONSTRAINT fkda8cf54726d6721f6b24c835b226efee FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2307 (class 2606 OID 43877)
-- Name: opsortie fkda8cf54726d6721f6b24c835c72ac49b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fkda8cf54726d6721f6b24c835c72ac49b FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2300 (class 2606 OID 43842)
-- Name: opsaisie fkda8cf54726d6721fc6616075; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsaisie
    ADD CONSTRAINT fkda8cf54726d6721fc6616075 FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2296 (class 2606 OID 43822)
-- Name: opentreearticle fkda8cf5473379fcf7162112ae; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreearticle
    ADD CONSTRAINT fkda8cf5473379fcf7162112ae FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2309 (class 2606 OID 43887)
-- Name: opsortiearticle fkda8cf5473379fcf738d84bdb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortiearticle
    ADD CONSTRAINT fkda8cf5473379fcf738d84bdb FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2292 (class 2606 OID 43802)
-- Name: opdettachement fkda8cf5473379fcf73f0d58df; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT fkda8cf5473379fcf73f0d58df FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2288 (class 2606 OID 43782)
-- Name: opattribution fkda8cf5473379fcf7491893be; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opattribution
    ADD CONSTRAINT fkda8cf5473379fcf7491893be FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2316 (class 2606 OID 43922)
-- Name: operationentree fkda8cf5473379fcf76b24c835b226efee; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY operationentree
    ADD CONSTRAINT fkda8cf5473379fcf76b24c835b226efee FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2305 (class 2606 OID 43867)
-- Name: opsortie fkda8cf5473379fcf76b24c835c72ac49b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fkda8cf5473379fcf76b24c835c72ac49b FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2299 (class 2606 OID 43837)
-- Name: opsaisie fkda8cf5473379fcf7c6616075; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsaisie
    ADD CONSTRAINT fkda8cf5473379fcf7c6616075 FOREIGN KEY (iddirection) REFERENCES direction(id);


-- Completed on 2018-07-05 11:56:51

--
-- PostgreSQL database dump complete
--
