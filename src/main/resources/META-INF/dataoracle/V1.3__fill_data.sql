-- INSERTING into ADIRESY
SET DEFINE OFF;
-- INSERTING into USERI
SET DEFINE OFF;
Insert into USERI (IDUSER,DESIGNATION,ROLE) values ('2','Dépositaire Comptable','ROLE_DC');
Insert into USERI (IDUSER,DESIGNATION,ROLE) values ('1','Administrateur','ROLE_ADMIN');
Insert into USERI (IDUSER,DESIGNATION,ROLE) values ('21','Direction','ROLE_DG');
Insert into USERI (IDUSER,DESIGNATION,ROLE) values ('23','Administrateur Général','ROLE_SISE');
Insert into USERI (IDUSER,DESIGNATION,ROLE) values ('41','Consultation','ROLE_CONSLOCAL');
Insert into USERI (IDUSER,DESIGNATION,ROLE) values ('42','GAC','ROLE_GAC');
Insert into USERI (IDUSER,DESIGNATION,ROLE) values ('43','Agent','ROLE_AGENT');
-- INSERTING into AGENT
SET DEFINE OFF;
Insert into AGENT (IDAGENT,ACTIVE,IM,NOMAGENT,PASSWORD,PRENOMAGENT,IDDIRECTION,IDPOSTENY,IDROLE) values ('1','1','999999','Administrateur','$2a$10$nVMDUP0w6Wji4FakfFNiBe2feKLK/oo4b9iFcKjXtqysCAAxN2/Py',null,null,null,'1');
-- INSERTING into DOUANE.ARTICLE
SET DEFINE OFF;
-- INSERTING into DOUANE.CATEGORIEMAT
SET DEFINE OFF;
-- INSERTING into DOUANE.CODEARTICLE
SET DEFINE OFF;
-- INSERTING into DOUANE.DESIGNATION
SET DEFINE OFF;
-- INSERTING into DEVISE
SET DEFINE OFF;
Insert into DEVISE (ID,DESIGNATION,DATEEXPIRE) values ('1','Tanindrazana - Fahafahana - Fandrosoana',to_date('01/01/18','DD/MM/RR'));
Insert into DEVISE (ID,DESIGNATION,DATEEXPIRE) values ('2','Fitiavana - Tanindrazana - Fandrosoana',to_date('02/01/18','DD/MM/RR'));
-- INSERTING into DIRECTION
SET DEFINE OFF;
Insert into DIRECTION (ID,DESIGNATION,BUDGET,CODEDIRECTION,QUATRE,TROIS) values ('3','Direction Générale des Douanes','Inconnue','DGD','Inconnue','Antananarivo');
Insert into DIRECTION (ID,DESIGNATION,BUDGET,CODEDIRECTION,QUATRE,TROIS) values ('5','Direction des Ressources et de la Formation','Inconnue','DRF','Inconnue','Antananarivo');
Insert into DIRECTION (ID,DESIGNATION,BUDGET,CODEDIRECTION,QUATRE,TROIS) values ('7','Service de l''informatique','Inconnue','SI','Inconnue','Antananarivo');
Insert into DIRECTION (ID,DESIGNATION,BUDGET,CODEDIRECTION,QUATRE,TROIS) values ('82','Direction de la Législation et de la Valeur','Inconnue','DLV','Inconnue','Antananarivo');
-- INSERTING into DIRECTIONTITLEHIST
SET DEFINE OFF;
Insert into DIRECTIONTITLEHIST (IDTITLE,DATEHIST,TITLE,IDDIRECTION) values ('4',to_date('27/08/18','DD/MM/RR'),'Direction Générale des Douanes','3');
Insert into DIRECTIONTITLEHIST (IDTITLE,DATEHIST,TITLE,IDDIRECTION) values ('6',to_date('27/08/18','DD/MM/RR'),'Direction des Ressources et de la Formation','5');
Insert into DIRECTIONTITLEHIST (IDTITLE,DATEHIST,TITLE,IDDIRECTION) values ('8',to_date('27/08/18','DD/MM/RR'),'Service de l''inforMAtique','7');
Insert into DIRECTIONTITLEHIST (IDTITLE,DATEHIST,TITLE,IDDIRECTION) values ('21',to_date('23/01/19','DD/MM/RR'),'Direction de la Législation et de la Valeur','82');
-- INSERTING into DIRECTIONHISTOR
SET DEFINE OFF;
Insert into DIRECTIONHISTOR (DIRID,DIRTITLEID) values ('3','4');
Insert into DIRECTIONHISTOR (DIRID,DIRTITLEID) values ('5','6');
Insert into DIRECTIONHISTOR (DIRID,DIRTITLEID) values ('7','8');
Insert into DIRECTIONHISTOR (DIRID,DIRTITLEID) values ('82','21');
-- INSERTING into ENTITYTEST
SET DEFINE OFF;
-- INSERTING into ETATMATERIEL
SET DEFINE OFF;
Insert into ETATMATERIEL (ID,DESIGNATION) values ('9','Bon');
Insert into ETATMATERIEL (ID,DESIGNATION) values ('10','Mauvais');
Insert into ETATMATERIEL (ID,DESIGNATION) values ('11','Moyen');
Insert into ETATMATERIEL (ID,DESIGNATION) values ('12','Sur cale');
Insert into ETATMATERIEL (ID,DESIGNATION) values ('13','En panne');
Insert into ETATMATERIEL (ID,DESIGNATION) values ('14','A condamner');
-- INSERTING into FINANCEMENT
SET DEFINE OFF;
Insert into FINANCEMENT (ID,DESIGNATION) values ('15','RPI');
Insert into FINANCEMENT (ID,DESIGNATION) values ('16','FASAD');
Insert into FINANCEMENT (ID,DESIGNATION) values ('17','GASYNET');
Insert into FINANCEMENT (ID,DESIGNATION) values ('18','PAGI');
Insert into FINANCEMENT (ID,DESIGNATION) values ('19','BANQUE MONDIALE');
Insert into FINANCEMENT (ID,DESIGNATION) values ('20','UNION EUROPEENE');
Insert into FINANCEMENT (ID,DESIGNATION) values ('21','OMD');
Insert into FINANCEMENT (ID,DESIGNATION) values ('22','Autres Partenaires techniques');
-- INSERTING into FOURNISSEUR
SET DEFINE OFF;
-- INSERTING into FOURNISSEURDETAIL
SET DEFINE OFF;
-- INSERTING into LOCALITE
SET DEFINE OFF;
-- INSERTING into MA
SET DEFINE OFF;
Insert into MA (ID,DESIGNATION) values ('37','Achat');
Insert into MA (ID,DESIGNATION) values ('38','Dons');
Insert into MA (ID,DESIGNATION) values ('39','Affectation');
Insert into MA (ID,DESIGNATION) values ('40','Recensement excédent');
-- INSERTING into MARQUE
SET DEFINE OFF;
Insert into MARQUE (ID,DESIGNATION) values ('23','Prolink');
Insert into MARQUE (ID,DESIGNATION) values ('24','Raffles');
Insert into MARQUE (ID,DESIGNATION) values ('25','Asus');
Insert into MARQUE (ID,DESIGNATION) values ('26','TOYOTA');
Insert into MARQUE (ID,DESIGNATION) values ('27','TOYOTA HILUX');
Insert into MARQUE (ID,DESIGNATION) values ('28','TOYOTA HIACE');
Insert into MARQUE (ID,DESIGNATION) values ('29','TOYOTA PRADO');
Insert into MARQUE (ID,DESIGNATION) values ('30','TOYOTA FORTUNER');
Insert into MARQUE (ID,DESIGNATION) values ('31','Nissan HARD BODY');
Insert into MARQUE (ID,DESIGNATION) values ('32','POLO CLASSIQUE');
Insert into MARQUE (ID,DESIGNATION) values ('33','ISUZU 4*4');
Insert into MARQUE (ID,DESIGNATION) values ('34','HP');
Insert into MARQUE (ID,DESIGNATION) values ('35','Inconnue');
Insert into MARQUE (ID,DESIGNATION) values ('36','Aucune');
-- INSERTING into MATERIEL
SET DEFINE OFF;
-- INSERTING into MOTIFDECHARGE
SET DEFINE OFF;
Insert into MOTIFDECHARGE (ID,DESIGNATION) values ('41','Affectation');
Insert into MOTIFDECHARGE (ID,DESIGNATION) values ('42','Déces');
Insert into MOTIFDECHARGE (ID,DESIGNATION) values ('43','Licenciement');
Insert into MOTIFDECHARGE (ID,DESIGNATION) values ('44','Démission');
Insert into MOTIFDECHARGE (ID,DESIGNATION) values ('45','Disponibilité');
Insert into MOTIFDECHARGE (ID,DESIGNATION) values ('46','Détachement');
-- INSERTING into MOTIFSORTIE
SET DEFINE OFF;
Insert into MOTIFSORTIE (ID,DESIGNATION) values ('47','Affectation');
Insert into MOTIFSORTIE (ID,DESIGNATION) values ('48','Perte');
Insert into MOTIFSORTIE (ID,DESIGNATION) values ('49','Condamnation');
Insert into MOTIFSORTIE (ID,DESIGNATION) values ('50','Recensement ');
-- INSERTING into NOMENCLATURE
SET DEFINE OFF;
Insert into NOMENCLATURE (ID,DESIGNATION,NOMENCLATURE) values ('51','Combustibles et Luminaires','1');
Insert into NOMENCLATURE (ID,DESIGNATION,NOMENCLATURE) values ('52','Effets d''habillement, équipement, campement, harnachement, pansage','2');
Insert into NOMENCLATURE (ID,DESIGNATION,NOMENCLATURE) values ('53','Meubles et objets d''ammeubement, literie et couchage','3');
Insert into NOMENCLATURE (ID,DESIGNATION,NOMENCLATURE) values ('54','Outillage, instrument et appareils divers, matériel de transport, instruments de musiquen matériel sportif, matériel de guerre','5');
Insert into NOMENCLATURE (ID,DESIGNATION,NOMENCLATURE) values ('55','Ouvrages de bibliothèques, de sciences, et arts, matériel d''enseignement, fournitures diverses','10');
-- INSERTING into OPATTRIBUTION
SET DEFINE OFF;
-- INSERTING into OPDETTACHEMENT
SET DEFINE OFF;
-- INSERTING into OPENTREEARTICLE
SET DEFINE OFF;
-- INSERTING into OPENTREEMATERIEL
SET DEFINE OFF;
-- INSERTING into OPERATIONENTREE
SET DEFINE OFF;
-- INSERTING into OPSAISIE
SET DEFINE OFF;
-- INSERTING into OPSORTIE
SET DEFINE OFF;
-- INSERTING into OPSORTIEARTICLE
SET DEFINE OFF;
-- INSERTING into POSTE
SET DEFINE OFF;
Insert into POSTE (ID,DESIGNATION) values ('56','Receveur');
Insert into POSTE (ID,DESIGNATION) values ('57','GAC');
Insert into POSTE (ID,DESIGNATION) values ('58','Dépositaire');
Insert into POSTE (ID,DESIGNATION) values ('59','GAC suppléant');
Insert into POSTE (ID,DESIGNATION) values ('60','Développeur');
Insert into POSTE (ID,DESIGNATION) values ('61','Directeur Général');
Insert into POSTE (ID,DESIGNATION) values ('62','Directeur');
Insert into POSTE (ID,DESIGNATION) values ('63','Directeur des Ressources et de la Formation');
Insert into POSTE (ID,DESIGNATION) values ('64','Chef SE');
Insert into POSTE (ID,DESIGNATION) values ('65','Administrateur');
Insert into POSTE (ID,DESIGNATION) values ('81','Dépositaire suppléant');
-- INSERTING into TYPEMATERIEL
SET DEFINE OFF;
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('66','Lampe tube fluorescent','LAM1','51');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('67','Réglettes LED','REG1','51');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('68','Projecteur LED','PRO1','51');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('69','Downlight LED','DOW1','51');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('70','TUBE LED','TUB1','51');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('71','Tenue de travail','TEN1','52');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('72','Tenue de cérémonie','TEN2','52');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('83','Effets blancs pour défilé','EFF1','52');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('84','Tenue de soirée','TEN3','52');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('85','Armoire En bois','ARM1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('86','Armoire Métallique','ARM2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('87','Armoire Mélaminé','ARM3','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('88','Armoire Autre','ARM','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('89','Bibliothèque En bois','BIB1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('90','Bibliothèque Métallique','BIB2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('91','Bibliothèque Mélaminé','BIB3','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('92','Bibliothèque Autre','BIB','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('93','Caisson mobile En bois','CAS1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('94','Caisson mobile Métallique','CAS2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('95','Caisson mobile Mélaminé','CAS3','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('96','Caisson mobile Autre','CAS','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('97','Coffre-fort','COF','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('98','Chaise De bureau','CHA1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('99','Chaise Visiteur Avec accoudoir','CHA_V1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('100','Chaise Visiteur Sans accoudoir','CHA_V2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('101','Chaise Rembourrée','CHA2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('102','Chaise De direction','CHA3','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('103','Chaise pliable','CHA4','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('104','Chaise Avec écritoire','CHA5','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('105','Chaise Fauteuil de bureau','CHA6','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('106','Salon canapé','CAN','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('107','Lit simple En bois','LIT1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('108','Lit simple Métallique ','LIS2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('109','Lit superposé Autre','LIS','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('110','Table De bureau En Bois','TBB1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('111','Table De bureau Métallique','TBB2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('112','Table De bureau Autre','TBB','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('113','Table De bureau Mélaminé','TBB3','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('114','Table Directeur En Bois','TAD1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('115','Table Directeur Métallique','TAD2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('116','Table Directeur Mélaminé','TAD3','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('117','Table Directeur Autre','TAD','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('118','Table De réunion En bois','TAR1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('119','Table De réunion Métallique','TAR2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('120','Table De réunion Mélaminé','TAR3','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('121','Table De réunion Autre ','TAR','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('122','Table ordinateur En bois','TAO1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('123','Table ordinateur Métallique','TAO2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('124','Table ordinateur Mélaminé','TAO3','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('125','Table ordinateur Autre','TAO','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('126','Table basse En bois','TAB1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('127','Table basse Métallique','TAB2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('128','Table basse Mélaminé','TAB3','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('129','Table basse Autre ','TAB','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('130','Tiroir de rangement En bois','TIR1','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('131','Tiroir de rangement Métallique','TIR2','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('132','Tiroir de rangement Mélaminé','TIR3','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('133','Tiroir de rangement Autre ','TIR','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('134','Buffet','BUF','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('135','Aspirateur','ASP','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('136','Lampe','LAP','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('137','Radio','RAD','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('138','Réfrigérateur','REF','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('139','Télévision','TV','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('140','Ventilateur','VEN','53');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('141','Ordinateur portable','ORP','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('142','Ordinateur de bureau complet','ORB','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('143','Unité centrale','UC','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('144','Souris','SOU','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('145','Clavier','CLA','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('146','Disque dure interne','DDI','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('147','Disque dure externe','DDE','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('148','Usb disque','USB','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('149','Ram','RAM','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('150','Imprimante','IMP','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('151','Imprimante multifonction','IMP1','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('152','Imprimante/photocopieur','IMP2','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('153','Scanner','SCA','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('154','Photocopieur','PIE','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('155','Onduleur','OND','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('156','Onduleur redondant','OND1','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('157','Câble FTP','CABF','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('158','Connecteur RJ45','CRJ45','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('159','Routeur','ROU','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('160','Routeur WIFI','ROU1','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('161','Switch','SWI','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('162','Rallonge','RAL','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('163','Videoprojecteur','VIP','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('164','Câble d’alimentation','CBL','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('165','Câble VGA','CBL1','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('166','Câble HDMI','CBL2','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('167','Câble DVI','CBL3','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('168','Boîte d’alimentation','BOI','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('169','Tablette de visite','TBL','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('170','Aimant en barre','AIM','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('171','Appareil photo','APP','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('172','Balance','BAL','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('173','Caméra','CAM','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('174','Détecteur de faux billets','DET','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('175','Dictaphone','DIC','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('176','Ecran de projection','ECR','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('177','Escabeau','ESC','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('178','Fax','FAX','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('179','Groupe électrogène','GRE','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('180','Jumelles','JUM','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('181','Machine à écrire','MCE','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('182','Machine à reliure','MCR','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('183','Magnétomètres','MAG','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('184','Menotte','MEN','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('185','Micro','MIC','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('186','Pince à plomb','PIP','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('187','Présentoir pliable','PRP','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('188','Prise multiple','PRI','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('189','Talkie-Walkie','TLW','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('191','Journal','JRL','55');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('192','Manuel de procédure','MAP','55');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('193','Recueil de Texte','RECT','55');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('194','Document','DOC','55');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('195','Livre','LIV','55');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('196','Clé à molette 20 cm poignée','CLE2','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('197','Clé anglaise','CLE1','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('198','Véhicules légères','VEH1','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('199','Moto Inférieur à 50 cc','MOT1','55');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('200','Téléphone portable','TEL2','54');
Insert into TYPEMATERIEL (ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values ('201','Bicyclette','BIC','54');
-- INSERTING into TYPEOBJET
SET DEFINE OFF;



-- SET SEQUENCE VALUE
ALTER SEQUENCE REF_SEQ INCREMENT BY 210;
SELECT REF_SEQ.nextval from DUAL;
ALTER SEQUENCE REF_SEQ INCREMENT BY 1;

--Insert into TYPEMATERIEL(ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values (MY_SEQ.nextval, 'Tenue de cérémonie', 'TEN2', 52);
