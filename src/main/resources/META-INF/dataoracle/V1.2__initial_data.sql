--------------------------------------------------------
--  DDL for Sequence ACCOUNT_IDART_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ACCOUNT_IDART_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence ACCOUNT_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ACCOUNT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence AG_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "AG_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence DESIGNATION_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "DESIGNATION_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence ENTEST_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "ENTEST_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence FOUR_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "FOUR_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence HIST_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "HIST_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence OP_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "OP_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence REF_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "REF_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence USERI_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "USERI_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence USERROLE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "USERROLE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;
--------------------------------------------------------
--  DDL for Sequence USER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "USER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE;

--------------------------------------------------------
--  DDL for Table ADIRESY
--------------------------------------------------------

  CREATE TABLE "ADIRESY" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table AGENT
--------------------------------------------------------

  CREATE TABLE "AGENT" 
   (	"IDAGENT" NUMBER(19,0), 
	"ACTIVE" NUMBER(1,0), 
	"IM" NUMBER(19,0), 
	"NOMAGENT" VARCHAR2(255 CHAR), 
	"PASSWORD" VARCHAR2(255 CHAR), 
	"PRENOMAGENT" VARCHAR2(255 CHAR), 
	"IDDIRECTION" NUMBER(19,0), 
	"IDPOSTENY" NUMBER(19,0), 
	"IDROLE" NUMBER(10,0)
   );
--------------------------------------------------------
--  DDL for Table ARTICLE
--------------------------------------------------------

  CREATE TABLE "ARTICLE" 
   (	"TYPEART" NUMBER(10,0), 
	"IDARTICLE" NUMBER(19,0), 
	"CARACTERISTIQUEARTICLE" VARCHAR2(255 CHAR), 
	"ESPECEUNIT" VARCHAR2(255 CHAR), 
	"NOMBRE" NUMBER(19,0), 
	"ORIGINE" VARCHAR2(255 CHAR), 
	"PRIX" FLOAT(126), 
	"REFERENCE" VARCHAR2(255 CHAR), 
	"VALIDATION" NUMBER(1,0), 
	"IMBENEFICIAIRE" NUMBER(19,0), 
	"IDCODE" NUMBER(19,0), 
	"IMDEPOSITAIRE" NUMBER(19,0), 
	"IDDIRECTIONART" NUMBER(19,0), 
	"IDMARQUEART" NUMBER(19,0), 
	"IDFIN" NUMBER(19,0), 
	"IDFOURNISSEUR" NUMBER(19,0), 
	"IDMODACQART" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table CATEGORIEMAT
--------------------------------------------------------

  CREATE TABLE "CATEGORIEMAT" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table CODEARTICLE
--------------------------------------------------------

  CREATE TABLE "CODEARTICLE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"IDTYPEOBJ" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table DESIGNATION
--------------------------------------------------------

  CREATE TABLE "DESIGNATION" 
   (	"IDDESIGNATION" NUMBER(19,0), 
	"ANNEEACQUISITION" VARCHAR2(255 CHAR), 
	"AUTRE" VARCHAR2(255 CHAR), 
	"DOCUMENTPATH" VARCHAR2(255 CHAR), 
	"ESPECEUNITE" VARCHAR2(255 CHAR), 
	"IMAGE" BLOB, 
	"ORIGINE" VARCHAR2(255 CHAR), 
	"PU" FLOAT(126), 
	"REFFACTURE" VARCHAR2(255 CHAR), 
	"RENSEIGNEMENT" VARCHAR2(255 CHAR), 
	"IDCAR" NUMBER(*,0), 
	"IDFIN" NUMBER(19,0), 
	"IDFOURN" NUMBER(19,0), 
	"IDMARQUE" NUMBER(19,0), 
	"IDMODACQ" NUMBER(19,0), 
	"IDNOM" NUMBER(19,0), 
	"IDTYPEMATERIEL" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table DEVISE
--------------------------------------------------------

  CREATE TABLE "DEVISE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"DATEEXPIRE" DATE
   );
--------------------------------------------------------
--  DDL for Table DIRECTION
--------------------------------------------------------

  CREATE TABLE "DIRECTION" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"BUDGET" VARCHAR2(255 CHAR), 
	"CODEDIRECTION" VARCHAR2(255 CHAR), 
	"QUATRE" VARCHAR2(255 CHAR), 
	"TROIS" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table DIRECTIONHISTOR
--------------------------------------------------------

  CREATE TABLE "DIRECTIONHISTOR" 
   (	"DIRID" NUMBER(19,0), 
	"DIRTITLEID" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table DIRECTIONTITLEHIST
--------------------------------------------------------

  CREATE TABLE "DIRECTIONTITLEHIST" 
   (	"IDTITLE" NUMBER(19,0), 
	"DATEHIST" DATE, 
	"TITLE" VARCHAR2(255 CHAR), 
	"IDDIRECTION" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table ENTITYTEST
--------------------------------------------------------

  CREATE TABLE "ENTITYTEST" 
   (	"IDENTIT" NUMBER(19,0), 
	"MYATTR" VARCHAR2(255 CHAR), 
	"MYDATE" DATE, 
	"TITLE" VARCHAR2(255 CHAR), 
	"IDDIRECTION" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table ETATMATERIEL
--------------------------------------------------------

  CREATE TABLE "ETATMATERIEL" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table FINANCEMENT
--------------------------------------------------------

  CREATE TABLE "FINANCEMENT" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table FOURNISSEUR
--------------------------------------------------------

  CREATE TABLE "FOURNISSEUR" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table FOURNISSEURDETAIL
--------------------------------------------------------

  CREATE TABLE "FOURNISSEURDETAIL" 
   (	"IDFOURN" NUMBER(19,0), 
	"ADRESSE" VARCHAR2(255 CHAR), 
	"CONTACT" VARCHAR2(255 CHAR), 
	"NIF" VARCHAR2(255 CHAR), 
	"NOMFOURN" VARCHAR2(255 CHAR), 
	"OBSERVATION" VARCHAR2(255 CHAR), 
	"STAT" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table LOCALITE
--------------------------------------------------------

  CREATE TABLE "LOCALITE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table MA
--------------------------------------------------------

  CREATE TABLE "MA" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table MARQUE
--------------------------------------------------------

  CREATE TABLE "MARQUE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table MATERIEL
--------------------------------------------------------

  CREATE TABLE "MATERIEL" 
   (	"TYPEMATERIELS" NUMBER(10,0), 
	"IDMATERIEL" NUMBER(19,0), 
	"ANNEEACQUISITION" VARCHAR2(255 CHAR), 
	"AUTRE" VARCHAR2(255 CHAR), 
	"CODE" VARCHAR2(255 CHAR), 
	"DOCUMENTPATH" VARCHAR2(255 CHAR), 
	"ESPECEUNITE" VARCHAR2(255 CHAR), 
	"IMAGE" RAW(255), 
	"NUMSERIE" VARCHAR2(255 CHAR), 
	"NUMEROTYPE" NUMBER(19,0), 
	"ORIGINE" VARCHAR2(255 CHAR), 
	"PU" FLOAT(126), 
	"REFERENCE" VARCHAR2(255 CHAR), 
	"RENSEIGNEMENT" VARCHAR2(255 CHAR), 
	"SERIENUMERO" NUMBER(10,0), 
	"VALIDATION" NUMBER(1,0), 
	"MONTANT_FACTURE" FLOAT(126), 
	"REFFACTURE" VARCHAR2(255 CHAR), 
	"IDCAR" NUMBER(*,0), 
	"IMDEPOSITAIRE" NUMBER(19,0), 
	"DESINGATIONID" NUMBER(19,0), 
	"IMDETENTEUR" NUMBER(19,0), 
	"IDDIRECTION" NUMBER(19,0), 
	"IDETAT" NUMBER(19,0), 
	"IDMARQUE" NUMBER(19,0), 
	"OPENTREEID" NUMBER(19,0), 
	"IDNOM" NUMBER(19,0), 
	"IDTYPEMATERIEL" NUMBER(19,0), 
	"IDFIN" NUMBER(19,0), 
	"IDFOURN" NUMBER(19,0), 
	"IDMODACQ" NUMBER(19,0), 
	"MATERIELCOUNT" NUMBER(10,0)
   );
--------------------------------------------------------
--  DDL for Table MOTIFDECHARGE
--------------------------------------------------------

  CREATE TABLE "MOTIFDECHARGE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table MOTIFSORTIE
--------------------------------------------------------

  CREATE TABLE "MOTIFSORTIE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table NOMENCLATURE
--------------------------------------------------------

  CREATE TABLE "NOMENCLATURE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"NOMENCLATURE" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table OPATTRIBUTION
--------------------------------------------------------

  CREATE TABLE "OPATTRIBUTION" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"DETENTEUREFFECTIF" VARCHAR2(255 CHAR), 
	"NUMERODET" VARCHAR2(255 CHAR), 
	"IDDETENTEUR" NUMBER(19,0), 
	"IDMAT" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table OPDETTACHEMENT
--------------------------------------------------------

  CREATE TABLE "OPDETTACHEMENT" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"IDDETENTEUR" NUMBER(19,0), 
	"IDMAT" NUMBER(19,0), 
	"IDMOTIFDETT" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table OPENTREEARTICLE
--------------------------------------------------------

  CREATE TABLE "OPENTREEARTICLE" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"IDART" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table OPENTREEMATERIEL
--------------------------------------------------------

  CREATE TABLE "OPENTREEMATERIEL" 
   (	"ENTREEID" NUMBER(19,0), 
	"MATERIELID" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table OPERATIONENTREE
--------------------------------------------------------

  CREATE TABLE "OPERATIONENTREE" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"NUMOPERATION" VARCHAR2(255 CHAR), 
	"IDMAT" NUMBER(19,0), 
	"PATHDOC" VARCHAR2(255 CHAR), 
	"REFFACT" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table OPSAISIE
--------------------------------------------------------

  CREATE TABLE "OPSAISIE" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"DESIGNATIONREF" VARCHAR2(255 CHAR), 
	"IDREFER" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table OPSORTIE
--------------------------------------------------------

  CREATE TABLE "OPSORTIE" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"NUMOPERATION" VARCHAR2(255 CHAR), 
	"IDMAT" NUMBER(19,0), 
	"PJ" VARCHAR2(255 CHAR), 
	"IDDIRECT" NUMBER(19,0), 
	"IDMOTIF" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table OPSORTIEARTICLE
--------------------------------------------------------

  CREATE TABLE "OPSORTIEARTICLE" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"DECISION" VARCHAR2(255 CHAR), 
	"NOMBRETOS" NUMBER(19,0), 
	"IDART" NUMBER(19,0), 
	"IDBENEFI" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table POSTE
--------------------------------------------------------

  CREATE TABLE "POSTE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table TYPEMATERIEL
--------------------------------------------------------

  CREATE TABLE "TYPEMATERIEL" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"CODETYPEMATE" VARCHAR2(255 CHAR), 
	"IDNOMENCLATURE" NUMBER(19,0)
   );
--------------------------------------------------------
--  DDL for Table TYPEOBJET
--------------------------------------------------------

  CREATE TABLE "TYPEOBJET" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"CARACTERISTIQUE" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table USERI
--------------------------------------------------------

  CREATE TABLE "USERI" 
   (	"IDUSER" NUMBER(10,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"ROLE" VARCHAR2(255 CHAR)
   );

--------------------------------------------------------
--  DDL for Index UK_983ENQ5T3N1WNDCH8EFIQYDVG
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_983ENQ5T3N1WNDCH8EFIQYDVG" ON "MATERIEL" ("CODE");
--------------------------------------------------------
--  DDL for Index UK_M14FS3CET7RKFDVM535GC19WW
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_M14FS3CET7RKFDVM535GC19WW" ON "AGENT" ("IM");
--------------------------------------------------------
--  DDL for Index UK_NBCGU0IESC6KP4JBL6L67CAS4
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_NBCGU0IESC6KP4JBL6L67CAS4" ON "TYPEMATERIEL" ("CODETYPEMATE");
--------------------------------------------------------
--  DDL for Index UK_IY7JSD0HVCXTQBISRXG0COYH5
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_IY7JSD0HVCXTQBISRXG0COYH5" ON "USERI" ("ROLE");
--------------------------------------------------------
--  DDL for Index UK_COE1WS0G9R615C2AK85E1FJWH
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_COE1WS0G9R615C2AK85E1FJWH" ON "NOMENCLATURE" ("NOMENCLATURE");
--------------------------------------------------------
--  DDL for Index UK_HAL2HTILJG342GMXTXCQRCRO7
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_HAL2HTILJG342GMXTXCQRCRO7" ON "OPENTREEMATERIEL" ("MATERIELID");
--------------------------------------------------------
--  DDL for Index UK_CC5KFF3LCCDUCT8UM9CIAB0ON
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_CC5KFF3LCCDUCT8UM9CIAB0ON" ON "DIRECTION" ("CODEDIRECTION");
--------------------------------------------------------
--  DDL for Index UK_7RINSRI5GER9MPCUJFFK0KCWA
--------------------------------------------------------

  CREATE UNIQUE INDEX "UK_7RINSRI5GER9MPCUJFFK0KCWA" ON "DIRECTIONHISTOR" ("DIRTITLEID");
  
  --------------------------------------------------------
--  Constraints for Table OPENTREEMATERIEL
--------------------------------------------------------

  ALTER TABLE "OPENTREEMATERIEL" ADD CONSTRAINT "UK_HAL2HTILJG342GMXTXCQRCRO7" UNIQUE ("MATERIELID") ENABLE;
  ALTER TABLE "OPENTREEMATERIEL" MODIFY ("MATERIELID" NOT NULL ENABLE);
  ALTER TABLE "OPENTREEMATERIEL" MODIFY ("ENTREEID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table USERI
--------------------------------------------------------

  ALTER TABLE "USERI" ADD CONSTRAINT "UK_IY7JSD0HVCXTQBISRXG0COYH5" UNIQUE ("ROLE") ENABLE;
  ALTER TABLE "USERI" ADD PRIMARY KEY ("IDUSER") ENABLE;
  ALTER TABLE "USERI" MODIFY ("IDUSER" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MOTIFSORTIE
--------------------------------------------------------

  ALTER TABLE "MOTIFSORTIE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "MOTIFSORTIE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ENTITYTEST
--------------------------------------------------------

  ALTER TABLE "ENTITYTEST" ADD PRIMARY KEY ("IDENTIT") ENABLE;
  ALTER TABLE "ENTITYTEST" MODIFY ("IDENTIT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table NOMENCLATURE
--------------------------------------------------------

  ALTER TABLE "NOMENCLATURE" ADD CONSTRAINT "UK_COE1WS0G9R615C2AK85E1FJWH" UNIQUE ("NOMENCLATURE") ENABLE;
  ALTER TABLE "NOMENCLATURE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "NOMENCLATURE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FOURNISSEUR
--------------------------------------------------------

  ALTER TABLE "FOURNISSEUR" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "FOURNISSEUR" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TYPEOBJET
--------------------------------------------------------

  ALTER TABLE "TYPEOBJET" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TYPEOBJET" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OPENTREEARTICLE
--------------------------------------------------------

  ALTER TABLE "OPENTREEARTICLE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OPENTREEARTICLE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OPATTRIBUTION
--------------------------------------------------------

  ALTER TABLE "OPATTRIBUTION" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OPATTRIBUTION" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ARTICLE
--------------------------------------------------------

  ALTER TABLE "ARTICLE" ADD PRIMARY KEY ("IDARTICLE") ENABLE;
  ALTER TABLE "ARTICLE" MODIFY ("VALIDATION" NOT NULL ENABLE);
  ALTER TABLE "ARTICLE" MODIFY ("IDARTICLE" NOT NULL ENABLE);
  ALTER TABLE "ARTICLE" MODIFY ("TYPEART" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OPDETTACHEMENT
--------------------------------------------------------

  ALTER TABLE "OPDETTACHEMENT" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OPDETTACHEMENT" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DIRECTIONHISTOR
--------------------------------------------------------

  ALTER TABLE "DIRECTIONHISTOR" ADD CONSTRAINT "UK_7RINSRI5GER9MPCUJFFK0KCWA" UNIQUE ("DIRTITLEID") ENABLE;
  ALTER TABLE "DIRECTIONHISTOR" MODIFY ("DIRTITLEID" NOT NULL ENABLE);
  ALTER TABLE "DIRECTIONHISTOR" MODIFY ("DIRID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MOTIFDECHARGE
--------------------------------------------------------

  ALTER TABLE "MOTIFDECHARGE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "MOTIFDECHARGE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FINANCEMENT
--------------------------------------------------------

  ALTER TABLE "FINANCEMENT" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "FINANCEMENT" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table LOCALITE
--------------------------------------------------------

  ALTER TABLE "LOCALITE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "LOCALITE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OPSORTIE
--------------------------------------------------------

  ALTER TABLE "OPSORTIE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OPSORTIE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DESIGNATION
--------------------------------------------------------

  ALTER TABLE "DESIGNATION" ADD PRIMARY KEY ("IDDESIGNATION") ENABLE;
  ALTER TABLE "DESIGNATION" MODIFY ("IDDESIGNATION" NOT NULL ENABLE);

--------------------------------------------------------
--  Constraints for Table DIRECTIONTITLEHIST
--------------------------------------------------------

  ALTER TABLE "DIRECTIONTITLEHIST" ADD PRIMARY KEY ("IDTITLE") ENABLE;
  ALTER TABLE "DIRECTIONTITLEHIST" MODIFY ("IDTITLE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MATERIEL
--------------------------------------------------------

  ALTER TABLE "MATERIEL" ADD CONSTRAINT "UK_983ENQ5T3N1WNDCH8EFIQYDVG" UNIQUE ("CODE") ENABLE;
  ALTER TABLE "MATERIEL" ADD PRIMARY KEY ("IDMATERIEL") ENABLE;
  ALTER TABLE "MATERIEL" MODIFY ("VALIDATION" NOT NULL ENABLE);
  ALTER TABLE "MATERIEL" MODIFY ("SERIENUMERO" NOT NULL ENABLE);
  ALTER TABLE "MATERIEL" MODIFY ("IDMATERIEL" NOT NULL ENABLE);
  ALTER TABLE "MATERIEL" MODIFY ("TYPEMATERIELS" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OPERATIONENTREE
--------------------------------------------------------

  ALTER TABLE "OPERATIONENTREE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OPERATIONENTREE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table POSTE
--------------------------------------------------------

  ALTER TABLE "POSTE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "POSTE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ETATMATERIEL
--------------------------------------------------------

  ALTER TABLE "ETATMATERIEL" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "ETATMATERIEL" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DIRECTION
--------------------------------------------------------

  ALTER TABLE "DIRECTION" ADD CONSTRAINT "UK_CC5KFF3LCCDUCT8UM9CIAB0ON" UNIQUE ("CODEDIRECTION") ENABLE;
  ALTER TABLE "DIRECTION" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "DIRECTION" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table ADIRESY
--------------------------------------------------------

  ALTER TABLE "ADIRESY" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "ADIRESY" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CODEARTICLE
--------------------------------------------------------

  ALTER TABLE "CODEARTICLE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "CODEARTICLE" MODIFY ("ID" NOT NULL ENABLE);

--------------------------------------------------------
--  Constraints for Table DEVISE
--------------------------------------------------------

  ALTER TABLE "DEVISE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "DEVISE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table AGENT
--------------------------------------------------------

  ALTER TABLE "AGENT" ADD CONSTRAINT "UK_M14FS3CET7RKFDVM535GC19WW" UNIQUE ("IM") ENABLE;
  ALTER TABLE "AGENT" ADD PRIMARY KEY ("IDAGENT") ENABLE;
  ALTER TABLE "AGENT" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "AGENT" MODIFY ("ACTIVE" NOT NULL ENABLE);
  ALTER TABLE "AGENT" MODIFY ("IDAGENT" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OPSAISIE
--------------------------------------------------------

  ALTER TABLE "OPSAISIE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OPSAISIE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FOURNISSEURDETAIL
--------------------------------------------------------

  ALTER TABLE "FOURNISSEURDETAIL" ADD PRIMARY KEY ("IDFOURN") ENABLE;
  ALTER TABLE "FOURNISSEURDETAIL" MODIFY ("IDFOURN" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table TYPEMATERIEL
--------------------------------------------------------

  ALTER TABLE "TYPEMATERIEL" ADD CONSTRAINT "UK_NBCGU0IESC6KP4JBL6L67CAS4" UNIQUE ("CODETYPEMATE") ENABLE;
  ALTER TABLE "TYPEMATERIEL" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "TYPEMATERIEL" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MARQUE
--------------------------------------------------------

  ALTER TABLE "MARQUE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "MARQUE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CATEGORIEMAT
--------------------------------------------------------

  ALTER TABLE "CATEGORIEMAT" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "CATEGORIEMAT" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table OPSORTIEARTICLE
--------------------------------------------------------

  ALTER TABLE "OPSORTIEARTICLE" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "OPSORTIEARTICLE" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MA
--------------------------------------------------------

  ALTER TABLE "MA" ADD PRIMARY KEY ("ID") ENABLE;
  ALTER TABLE "MA" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table AGENT
--------------------------------------------------------

  ALTER TABLE "AGENT" ADD CONSTRAINT "FK_GKCO9YX7753XUJU4L5FV7X0WC" FOREIGN KEY ("IDROLE")
	  REFERENCES "USERI" ("IDUSER") ENABLE;
  ALTER TABLE "AGENT" ADD CONSTRAINT "FK_RPCQQSBI9VYG5QEBUKAMXMPHH" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES "DIRECTION" ("ID") ENABLE;
  ALTER TABLE "AGENT" ADD CONSTRAINT "FK_TBHD06QF1W66E0QKIC6PPKKE4" FOREIGN KEY ("IDPOSTENY")
	  REFERENCES "POSTE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ARTICLE
--------------------------------------------------------

  ALTER TABLE "ARTICLE" ADD CONSTRAINT "FK_2FTX9F7EDRUX42EGI8P9STCW0" FOREIGN KEY ("IDFIN")
	  REFERENCES "FINANCEMENT" ("ID") ENABLE;
  ALTER TABLE "ARTICLE" ADD CONSTRAINT "FK_B2N61S2P8AS0I9XT1AA5MFECE" FOREIGN KEY ("IDCODE")
	  REFERENCES "CODEARTICLE" ("ID") ENABLE;
  ALTER TABLE "ARTICLE" ADD CONSTRAINT "FK_BTP963QJG0OQCXUTIFO9XWJ8N" FOREIGN KEY ("IDMARQUEART")
	  REFERENCES "MARQUE" ("ID") ENABLE;
  ALTER TABLE "ARTICLE" ADD CONSTRAINT "FK_E5J8X010A91PMA929BQ46FK7K" FOREIGN KEY ("IDFOURNISSEUR")
	  REFERENCES "FOURNISSEUR" ("ID") ENABLE;
  ALTER TABLE "ARTICLE" ADD CONSTRAINT "FK_G7W6PFQ4KQLPF0C5HDM43TB31" FOREIGN KEY ("IMBENEFICIAIRE")
	  REFERENCES "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE "ARTICLE" ADD CONSTRAINT "FK_HPFKDRSEFLF6I97GEL3LGO42K" FOREIGN KEY ("IDMODACQART")
	  REFERENCES "MA" ("ID") ENABLE;
  ALTER TABLE "ARTICLE" ADD CONSTRAINT "FK_IEXTL9K5I24WFO1Q1HSG2C3HA" FOREIGN KEY ("IMDEPOSITAIRE")
	  REFERENCES "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE "ARTICLE" ADD CONSTRAINT "FK_R9YT94J6ORLV39WVRXS0X80GJ" FOREIGN KEY ("IDDIRECTIONART")
	  REFERENCES "DIRECTION" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table CODEARTICLE
--------------------------------------------------------

  ALTER TABLE "CODEARTICLE" ADD CONSTRAINT "FK_CR7OUOPGWX0WSSX2C1QL4N9OD" FOREIGN KEY ("IDTYPEOBJ")
	  REFERENCES "TYPEOBJET" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DESIGNATION
--------------------------------------------------------

  ALTER TABLE "DESIGNATION" ADD CONSTRAINT "FK_41Q8UYOV72OJ3RF2PBK7XTX8B" FOREIGN KEY ("IDFIN")
	  REFERENCES "FINANCEMENT" ("ID") ENABLE;
  ALTER TABLE "DESIGNATION" ADD CONSTRAINT "FK_BAN4IW8WXYKH1QGEY0VNFQBQ0" FOREIGN KEY ("IDMODACQ")
	  REFERENCES "MA" ("ID") ENABLE;
  ALTER TABLE  "DESIGNATION" ADD CONSTRAINT "FK_BK80XVLPMKKPF8ESTRWV6G7I6" FOREIGN KEY ("IDFOURN")
	  REFERENCES  "FOURNISSEUR" ("ID") ENABLE;
  ALTER TABLE  "DESIGNATION" ADD CONSTRAINT "FK_CKRCLNXLP2J9HJLG53Y13I478" FOREIGN KEY ("IDMARQUE")
	  REFERENCES  "MARQUE" ("ID") ENABLE;
  ALTER TABLE  "DESIGNATION" ADD CONSTRAINT "FK_JN9FHDF4A7DX9WFM10SRWGWGY" FOREIGN KEY ("IDTYPEMATERIEL")
	  REFERENCES  "TYPEMATERIEL" ("ID") ENABLE;
  ALTER TABLE  "DESIGNATION" ADD CONSTRAINT "FK_NU5V8MJCQQ3JSHVHDSXMMFVGE" FOREIGN KEY ("IDNOM")
	  REFERENCES  "NOMENCLATURE" ("ID") ENABLE;
  ALTER TABLE  "DESIGNATION" ADD CONSTRAINT "FK_PU91RY2RKVT1UXIETF394T353" FOREIGN KEY ("IDCAR")
	  REFERENCES  "TYPEMATERIEL" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DIRECTIONHISTOR
--------------------------------------------------------

  ALTER TABLE  "DIRECTIONHISTOR" ADD CONSTRAINT "FK_7RINSRI5GER9MPCUJFFK0KCWA" FOREIGN KEY ("DIRTITLEID")
	  REFERENCES  "DIRECTIONTITLEHIST" ("IDTITLE") ENABLE;
  ALTER TABLE  "DIRECTIONHISTOR" ADD CONSTRAINT "FK_B6HKVADIJTIO2W9HHWVD1P7L5" FOREIGN KEY ("DIRID")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DIRECTIONTITLEHIST
--------------------------------------------------------

  ALTER TABLE  "DIRECTIONTITLEHIST" ADD CONSTRAINT "FK_G7L5WT1T2J2FUPOM91OYMHENM" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ENTITYTEST
--------------------------------------------------------

  ALTER TABLE  "ENTITYTEST" ADD CONSTRAINT "FK_I0VBPG4GY83886HXFKBVX9UOT" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MATERIEL
--------------------------------------------------------

  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_1KFD3LTF9M8POISH73OSJUFVX" FOREIGN KEY ("IDETAT")
	  REFERENCES  "ETATMATERIEL" ("ID") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_5BUGQ7T3XH225S002U3VFW1US" FOREIGN KEY ("IMDEPOSITAIRE")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_63XRUNVAVFWAVIG1LKSQX5GIJ" FOREIGN KEY ("IDCAR")
	  REFERENCES  "TYPEMATERIEL" ("ID") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_AOE0UTLRXP6HUEVPRWJBCG6" FOREIGN KEY ("DESINGATIONID")
	  REFERENCES  "DESIGNATION" ("IDDESIGNATION") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_FU77NA9AT6MCAS0M3FGQILLB9" FOREIGN KEY ("OPENTREEID")
	  REFERENCES  "OPERATIONENTREE" ("ID") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_GBKF6SENO6BDA3RU2B7RIR6IC" FOREIGN KEY ("IDMARQUE")
	  REFERENCES  "MARQUE" ("ID") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_K069NWTGGGQVDHPCF5DBFVUHM" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_LD53T3UO6GM3TMOUJLXIDX6Y" FOREIGN KEY ("IMDETENTEUR")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_MXU71STFOSTA9UHTQ19OB6YQ2" FOREIGN KEY ("IDMODACQ")
	  REFERENCES  "MA" ("ID") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_NIUUFL4C4FFBCB3JQEIOF9BI2" FOREIGN KEY ("IDTYPEMATERIEL")
	  REFERENCES  "TYPEMATERIEL" ("ID") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_PTPFKXR6G5EDDGS0GQYED65AW" FOREIGN KEY ("IDFOURN")
	  REFERENCES  "FOURNISSEUR" ("ID") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_QGP53RK76Q65RLW5JY381M0KN" FOREIGN KEY ("IDFIN")
	  REFERENCES  "FINANCEMENT" ("ID") ENABLE;
  ALTER TABLE  "MATERIEL" ADD CONSTRAINT "FK_S60C8L417U3DVLPSXJNNDAVCN" FOREIGN KEY ("IDNOM")
	  REFERENCES  "NOMENCLATURE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OPATTRIBUTION
--------------------------------------------------------

  ALTER TABLE  "OPATTRIBUTION" ADD CONSTRAINT "FK_8DPLL1C9PEH7TQQG4RW7A5Q3K" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
  ALTER TABLE  "OPATTRIBUTION" ADD CONSTRAINT "FK_97LMO48SK98WJAJBPJ79DTRCL" FOREIGN KEY ("IDDETENTEUR")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE  "OPATTRIBUTION" ADD CONSTRAINT "FK_RDPKAECO02SOVQCXSYAK77SIR" FOREIGN KEY ("IDMAT")
	  REFERENCES  "MATERIEL" ("IDMATERIEL") ENABLE;
  ALTER TABLE  "OPATTRIBUTION" ADD CONSTRAINT "FK_TE4TC5E28V67S3KO7Q28QGRQJ" FOREIGN KEY ("IDOPERATEUR")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OPDETTACHEMENT
--------------------------------------------------------

  ALTER TABLE  "OPDETTACHEMENT" ADD CONSTRAINT "FK_96GH25IAABMNW71MLO3L67HI1" FOREIGN KEY ("IDOPERATEUR")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE  "OPDETTACHEMENT" ADD CONSTRAINT "FK_EFSPMXW039473C7AY04QJ773C" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
  ALTER TABLE  "OPDETTACHEMENT" ADD CONSTRAINT "FK_J43BA6YRYFIP7DBW7CVR1UUAO" FOREIGN KEY ("IDDETENTEUR")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE  "OPDETTACHEMENT" ADD CONSTRAINT "FK_J6Q293LK4TBIOI6TGV6HKR9X0" FOREIGN KEY ("IDMOTIFDETT")
	  REFERENCES  "MOTIFDECHARGE" ("ID") ENABLE;
  ALTER TABLE  "OPDETTACHEMENT" ADD CONSTRAINT "FK_S1EC3JB4PL769VI9WYBEYF2EA" FOREIGN KEY ("IDMAT")
	  REFERENCES  "MATERIEL" ("IDMATERIEL") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OPENTREEARTICLE
--------------------------------------------------------

  ALTER TABLE  "OPENTREEARTICLE" ADD CONSTRAINT "FK_A13A3JI8DWJ6TV5QBN0MW224K" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
  ALTER TABLE  "OPENTREEARTICLE" ADD CONSTRAINT "FK_BRF7C5DVG7PN4AIK8UNXYDYBN" FOREIGN KEY ("IDOPERATEUR")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE  "OPENTREEARTICLE" ADD CONSTRAINT "FK_EPDJU6822RFQ4JNXCX3UQG00" FOREIGN KEY ("IDART")
	  REFERENCES  "ARTICLE" ("IDARTICLE") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OPENTREEMATERIEL
--------------------------------------------------------

  ALTER TABLE  "OPENTREEMATERIEL" ADD CONSTRAINT "FK_HAL2HTILJG342GMXTXCQRCRO7" FOREIGN KEY ("MATERIELID")
	  REFERENCES  "MATERIEL" ("IDMATERIEL") ENABLE;
  ALTER TABLE  "OPENTREEMATERIEL" ADD CONSTRAINT "FK_JQTUVQV8QOI5Y9P7U48L3NYM4" FOREIGN KEY ("ENTREEID")
	  REFERENCES  "OPERATIONENTREE" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OPERATIONENTREE
--------------------------------------------------------

  ALTER TABLE  "OPERATIONENTREE" ADD CONSTRAINT "FK_BWI2WESV4MOINNE8M49QOQD2G" FOREIGN KEY ("IDOPERATEUR")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE  "OPERATIONENTREE" ADD CONSTRAINT "FK_EQ418UBA8SIQHJ80CL16XBSG3" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
  ALTER TABLE  "OPERATIONENTREE" ADD CONSTRAINT "FK_FI9R4DEQGXGF02SE7CMKHWNN0" FOREIGN KEY ("IDMAT")
	  REFERENCES  "MATERIEL" ("IDMATERIEL") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OPSAISIE
--------------------------------------------------------

  ALTER TABLE  "OPSAISIE" ADD CONSTRAINT "FK_MNWVE0UF1VJV90XB1Y9CW3GLR" FOREIGN KEY ("IDOPERATEUR")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE  "OPSAISIE" ADD CONSTRAINT "FK_R5OKYP62DIFOD2XFVLYYX3HEW" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OPSORTIE
--------------------------------------------------------

  ALTER TABLE  "OPSORTIE" ADD CONSTRAINT "FK_2QMEK8D2IFFIOEPM4WVTD7Q10" FOREIGN KEY ("IDMAT")
	  REFERENCES  "MATERIEL" ("IDMATERIEL") ENABLE;
  ALTER TABLE  "OPSORTIE" ADD CONSTRAINT "FK_3GSDSKE3SK9Q0VPOLSEFKAWN8" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
  ALTER TABLE  "OPSORTIE" ADD CONSTRAINT "FK_C023JK8IO7HCJ73Y8KCM8U1J7" FOREIGN KEY ("IDDIRECT")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
  ALTER TABLE  "OPSORTIE" ADD CONSTRAINT "FK_DMPUXSN6BRPKTGYFBS9T260FB" FOREIGN KEY ("IDMOTIF")
	  REFERENCES  "MOTIFSORTIE" ("ID") ENABLE;
  ALTER TABLE  "OPSORTIE" ADD CONSTRAINT "FK_DOD7CY4PW817NLKJR1FA70ML8" FOREIGN KEY ("IDOPERATEUR")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table OPSORTIEARTICLE
--------------------------------------------------------

  ALTER TABLE  "OPSORTIEARTICLE" ADD CONSTRAINT "FK_3704H5OG1V9S8PQ8IFQAOS6G7" FOREIGN KEY ("IDART")
	  REFERENCES  "ARTICLE" ("IDARTICLE") ENABLE;
  ALTER TABLE  "OPSORTIEARTICLE" ADD CONSTRAINT "FK_ET3KOLBB4LF3142MX4U0SYGWI" FOREIGN KEY ("IDDIRECTION")
	  REFERENCES  "DIRECTION" ("ID") ENABLE;
  ALTER TABLE  "OPSORTIEARTICLE" ADD CONSTRAINT "FK_NDG3QDKN7Y7URO6877HBQDETX" FOREIGN KEY ("IDBENEFI")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
  ALTER TABLE  "OPSORTIEARTICLE" ADD CONSTRAINT "FK_RE4D3WS1K37OCI5DUTEFL7Y6V" FOREIGN KEY ("IDOPERATEUR")
	  REFERENCES  "AGENT" ("IDAGENT") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table TYPEMATERIEL
--------------------------------------------------------

  ALTER TABLE  "TYPEMATERIEL" ADD CONSTRAINT "FK_ABTV5K232S1NHKX0140E3RJ9F" FOREIGN KEY ("IDNOMENCLATURE")
	  REFERENCES  "NOMENCLATURE" ("ID") ENABLE;

