/*
 Navicat Premium Data Transfer

 Source Server         : post horst
 Source Server Type    : PostgreSQL
 Source Server Version : 90304
 Source Host           : localhost
 Source Database       : postgres
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 90304
 File Encoding         : utf-8

 Date: 05/20/2014 11:58:52 AM
*/

-- ----------------------------
--  Sequence structure for cams_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."cams_id_seq";
CREATE SEQUENCE "public"."cams_id_seq" INCREMENT 1 START 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."cams_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for pictures_cam_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pictures_cam_id_seq";
CREATE SEQUENCE "public"."pictures_cam_id_seq" INCREMENT 1 START 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."pictures_cam_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Sequence structure for pictures_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pictures_id_seq";
CREATE SEQUENCE "public"."pictures_id_seq" INCREMENT 1 START 1 MAXVALUE 9223372036854775807 MINVALUE 1 CACHE 1;
ALTER TABLE "public"."pictures_id_seq" OWNER TO "postgres";

-- ----------------------------
--  Table structure for cams
-- ----------------------------
DROP TABLE IF EXISTS "public"."cams";
CREATE TABLE "public"."cams" (
	"id" int4 NOT NULL DEFAULT nextval('cams_id_seq'::regclass),
	"name" varchar(50) COLLATE "default",
	"url" varchar(127) COLLATE "default"
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."cams" OWNER TO "postgres";

-- ----------------------------
--  Table structure for pictures
-- ----------------------------
DROP TABLE IF EXISTS "public"."pictures";
CREATE TABLE "public"."pictures" (
	"id" int4 NOT NULL DEFAULT nextval('pictures_id_seq'::regclass),
	"time" timestamp(6) NULL,
	"path" varchar(500) COLLATE "default",
	"cam_id" int4 NOT NULL DEFAULT nextval('pictures_cam_id_seq'::regclass)
)
WITH (OIDS=FALSE);
ALTER TABLE "public"."pictures" OWNER TO "postgres";


-- ----------------------------
--  Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."cams_id_seq" RESTART 2 OWNED BY "cams"."id";
ALTER SEQUENCE "public"."pictures_cam_id_seq" RESTART 2 OWNED BY "pictures"."cam_id";
ALTER SEQUENCE "public"."pictures_id_seq" RESTART 2 OWNED BY "pictures"."id";
-- ----------------------------
--  Primary key structure for table cams
-- ----------------------------
ALTER TABLE "public"."cams" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Indexes structure for table cams
-- ----------------------------
CREATE UNIQUE INDEX  "cams_id_key" ON "public"."cams" USING btree("id" ASC NULLS LAST);

-- ----------------------------
--  Triggers structure for table cams
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16424" AFTER UPDATE ON "public"."cams" FROM "public"."pictures" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_noaction_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16424" ON "public"."cams" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_a_16423" AFTER DELETE ON "public"."cams" FROM "public"."pictures" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_setnull_del"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_a_16423" ON "public"."cams" IS NULL;

-- ----------------------------
--  Primary key structure for table pictures
-- ----------------------------
ALTER TABLE "public"."pictures" ADD PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE;

-- ----------------------------
--  Triggers structure for table pictures
-- ----------------------------
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16426" AFTER UPDATE ON "public"."pictures" FROM "public"."cams" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_upd"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16426" ON "public"."pictures" IS NULL;
CREATE CONSTRAINT TRIGGER "RI_ConstraintTrigger_c_16425" AFTER INSERT ON "public"."pictures" FROM "public"."cams" NOT DEFERRABLE INITIALLY IMMEDIATE FOR EACH ROW EXECUTE PROCEDURE "RI_FKey_check_ins"();
COMMENT ON TRIGGER "RI_ConstraintTrigger_c_16425" ON "public"."pictures" IS NULL;

-- ----------------------------
--  Foreign keys structure for table pictures
-- ----------------------------
ALTER TABLE "public"."pictures" ADD CONSTRAINT "cam" FOREIGN KEY ("cam_id") REFERENCES "public"."cams" ("id") ON UPDATE NO ACTION ON DELETE SET NULL NOT DEFERRABLE INITIALLY IMMEDIATE;

