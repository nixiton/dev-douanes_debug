--
-- TOC entry 2157 (class 0 OID 323781)
-- Dependencies: 176
-- Data for Name: agent; Type: TABLE DATA; Schema: public; Owner: -
--


--
-- TOC entry 2195 (class 0 OID 323945)
-- Dependencies: 214
-- Data for Name: useri; Type: TABLE DATA; Schema: public; Owner: -
--

--INSERT INTO useri VALUES (1, 'Administrateur', 'ROLE_ADMIN');

--
-- TOC entry 2164 (class 0 OID 324008)
-- Dependencies: 183
-- Data for Name: devise; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO devise VALUES (1, 'Tanindrazana - Fahafahana - Fandrosoana', '2018-01-01');
INSERT INTO devise VALUES (2, 'Fitiavana - Tanindrazana - Fandrosoana', '2018-01-02');

--
-- TOC entry 2165 (class 0 OID 324011)
-- Dependencies: 184
-- Data for Name: direction; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO direction VALUES (3, 'Direction Générale des Douanes', 'Inconnue', 'DGD', 'Inconnue', 'Antananarivo');
INSERT INTO direction VALUES (5, 'Direction des Ressources et de la Formation', 'Inconnue', 'DRF', 'Inconnue', 'Antananarivo');
INSERT INTO direction VALUES (7, 'Service de l''informatique', 'Inconnue', 'SI', 'Inconnue', 'Antananarivo');

--
-- TOC entry 2166 (class 0 OID 324017)
-- Dependencies: 185
-- Data for Name: directionhistor; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO directionhistor VALUES (3, 4);
INSERT INTO directionhistor VALUES (5, 6);
INSERT INTO directionhistor VALUES (7, 8);

--
-- TOC entry 2167 (class 0 OID 324020)
-- Dependencies: 186
-- Data for Name: directiontitlehist; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO directiontitlehist VALUES (4, '2018-08-27', 'Direction Générale des Douanes', 3);
INSERT INTO directiontitlehist VALUES (6, '2018-08-27', 'Direction des Ressources et de la Formation', 5);
INSERT INTO directiontitlehist VALUES (8, '2018-08-27', 'Service de l''informatique', 7);

--
-- TOC entry 2168 (class 0 OID 324023)
-- Dependencies: 187
-- Data for Name: etatmateriel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO etatmateriel VALUES (9, 'Bon');
INSERT INTO etatmateriel VALUES (10, 'Mauvais');
INSERT INTO etatmateriel VALUES (11, 'Moyen');
INSERT INTO etatmateriel VALUES (12, 'Sur cale');
INSERT INTO etatmateriel VALUES (13, 'En panne');
INSERT INTO etatmateriel VALUES (14, 'A condamner');

--
-- TOC entry 2169 (class 0 OID 324026)
-- Dependencies: 188
-- Data for Name: financement; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO financement VALUES (15, 'RPI');
INSERT INTO financement VALUES (16, 'FASAD');
INSERT INTO financement VALUES (17, 'GASYNET');
INSERT INTO financement VALUES (18, 'PAGI');
INSERT INTO financement VALUES (19, 'BANQUE MONDIALE');
INSERT INTO financement VALUES (20, 'UNION EUROPEENE');
INSERT INTO financement VALUES (21, 'OMD');
INSERT INTO financement VALUES (22, 'Autres Partenaires techniques');

--
-- TOC entry 2175 (class 0 OID 324046)
-- Dependencies: 194
-- Data for Name: ma; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO ma VALUES (37, 'Achat');
INSERT INTO ma VALUES (38, 'Dons');
INSERT INTO ma VALUES (39, 'Affectation');
INSERT INTO ma VALUES (40, 'Recensement excédent');

--
-- TOC entry 2176 (class 0 OID 324049)
-- Dependencies: 195
-- Data for Name: marque; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO marque VALUES (23, 'Prolink');
INSERT INTO marque VALUES (24, 'Raffles');
INSERT INTO marque VALUES (25, 'Asus');
INSERT INTO marque VALUES (26, 'TOYOTA');
INSERT INTO marque VALUES (27, 'TOYOTA HILUX');
INSERT INTO marque VALUES (28, 'TOYOTA HIACE');
INSERT INTO marque VALUES (29, 'TOYOTA PRADO');
INSERT INTO marque VALUES (30, 'TOYOTA FORTUNER');
INSERT INTO marque VALUES (31, 'Nissan HARD BODY');
INSERT INTO marque VALUES (32, 'POLO CLASSIQUE');
INSERT INTO marque VALUES (33, 'ISUZU 4*4');
INSERT INTO marque VALUES (34, 'HP');
INSERT INTO marque VALUES (35, 'Inconnue');
INSERT INTO marque VALUES (36, 'Aucune');

--
-- TOC entry 2178 (class 0 OID 324058)
-- Dependencies: 197
-- Data for Name: motifdecharge; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO motifdecharge VALUES (41, 'Affectation');
INSERT INTO motifdecharge VALUES (42, 'Déces');
INSERT INTO motifdecharge VALUES (43, 'Licenciement');
INSERT INTO motifdecharge VALUES (44, 'Démission');
INSERT INTO motifdecharge VALUES (45, 'Disponibilité');
INSERT INTO motifdecharge VALUES (46, 'Détachement');
--
-- TOC entry 2179 (class 0 OID 324061)
-- Dependencies: 198
-- Data for Name: motifsortie; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO motifsortie VALUES (47, 'Affectation');
INSERT INTO motifsortie VALUES (48, 'Perte');
INSERT INTO motifsortie VALUES (49, 'Condamnation');
INSERT INTO motifsortie VALUES (50, 'Recensement ');
--
-- TOC entry 2180 (class 0 OID 324064)
-- Dependencies: 199
-- Data for Name: nomenclature; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO nomenclature VALUES (51, 'Combustibles et Luminaires', '1');
INSERT INTO nomenclature VALUES (52, 'Effets d''habillement, équipement, campement, harnachement, pansage', '2');
INSERT INTO nomenclature VALUES (53, 'Meubles et objets d''ammeubement, literie et couchage', '3');
INSERT INTO nomenclature VALUES (54, 'Outillage, instrument et appareils divers, matériel de transport, instruments de musiquen matériel sportif, matériel de guerre', '5');
INSERT INTO nomenclature VALUES (55, 'Ouvrages de bibliothèques, de sciences, et arts, Matériel d''enseignement, fournitures diverses', '10');

--
-- TOC entry 2190 (class 0 OID 324117)
-- Dependencies: 209
-- Data for Name: poste; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO poste VALUES (56, 'Receveur');
INSERT INTO poste VALUES (57, 'GAC');
INSERT INTO poste VALUES (58, 'Depositaire');
INSERT INTO poste VALUES (59, 'GAC suppléant');
INSERT INTO poste VALUES (60, 'Développeur');
INSERT INTO poste VALUES (61, 'Directeur Général');
INSERT INTO poste VALUES (62, 'Directeur');
INSERT INTO poste VALUES (63, 'Directeur des Ressources et de la Formation');
INSERT INTO poste VALUES (64, 'Chef SE');
INSERT INTO poste VALUES (65, 'Administrateur');

--
-- TOC entry 2193 (class 0 OID 324128)
-- Dependencies: 212
-- Data for Name: typemateriel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typemateriel VALUES (66, 'Lampe tube fluorescent', 'LAM1', 51);
INSERT INTO typemateriel VALUES (67, 'Réglettes LED', 'REG1', 51);
INSERT INTO typemateriel VALUES (68, 'Projecteur LED', 'PRO1', 51);
INSERT INTO typemateriel VALUES (69, 'Downlight LED', 'DOW1', 51);
INSERT INTO typemateriel VALUES (70, 'TUBE LED', 'TUB1', 51);
INSERT INTO typemateriel VALUES (71, 'Tenue de travail', 'TEN1', 52);
INSERT INTO typemateriel VALUES (72, 'Tenue de cérémonie', 'TEN2', 52);

--
-- TOC entry 2195 (class 0 OID 324140)
-- Dependencies: 214
-- Data for Name: useri; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO useri VALUES (2, 'Dépositaire Comptable', 'ROLE_DC');

INSERT INTO useri VALUES (1, 'Administrateur', 'ROLE_ADMIN');

INSERT INTO agent VALUES (1, true, 999999, 'Administrateur', '$2a$10$nVMDUP0w6Wji4FakfFNiBe2feKLK/oo4b9iFcKjXtqysCAAxN2/Py', NULL, NULL, NULL, 1);

--
-- TOC entry 2213 (class 0 OID 0)
-- Dependencies: 215
-- Name: useri_iduser_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('useri_iduser_seq', 1, true);




--
-- TOC entry 2212 (class 0 OID 0)
-- Dependencies: 210
-- Name: ref_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('ref_seq', 72, true);
