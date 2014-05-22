-- Table: cam

-- DROP TABLE cam;

CREATE TABLE cam
(
  id serial NOT NULL,
  name character varying(50),
  url character varying(127),
  status character varying(1),
  CONSTRAINT cams_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cam
  OWNER TO postgres;

  -- Table: picture

  -- DROP TABLE picture;

CREATE TABLE picture
(
  id serial NOT NULL,
  "time" timestamp without time zone DEFAULT now(),
  path character varying(127),
  cam_id integer,
  CONSTRAINT pictures_pkey PRIMARY KEY (id),
  CONSTRAINT pictures_cam_id_fkey FOREIGN KEY (cam_id)
      REFERENCES cam (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE picture
  OWNER TO postgres;

  -- Table: "user"

-- DROP TABLE "user";

CREATE TABLE "user"
(
  id serial NOT NULL,
  username character varying(20) NOT NULL,
  password character varying(255) NOT NULL,
  saltvalue character varying(5) NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "user"
  OWNER TO postgres;

 -- Table: "group"

-- DROP TABLE "group";

CREATE TABLE "group"
(
  id serial NOT NULL,
  name character varying(30) NOT NULL,
  CONSTRAINT group_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "group"
  OWNER TO postgres;

  -- Table: usertogroup

-- DROP TABLE usertogroup;

CREATE TABLE usertogroup
(
  userid integer NOT NULL,
  groupid integer NOT NULL,
  CONSTRAINT usertogroup_pkey PRIMARY KEY (userid, groupid),
  CONSTRAINT usertogroup_groupid_fkey FOREIGN KEY (groupid)
      REFERENCES "group" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT usertogroup_userid_fkey FOREIGN KEY (userid)
      REFERENCES "user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE usertogroup
  OWNER TO postgres;

  -- Table: camtouser

-- DROP TABLE camtouser;

CREATE TABLE camtouser
(
  userid integer NOT NULL,
  camid integer NOT NULL,
  CONSTRAINT camtouser_pkey PRIMARY KEY (userid, camid),
  CONSTRAINT camtouser_camid_fkey FOREIGN KEY (camid)
      REFERENCES cam (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT camtouser_userid_fkey FOREIGN KEY (userid)
      REFERENCES "user" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE camtouser
  OWNER TO postgres;