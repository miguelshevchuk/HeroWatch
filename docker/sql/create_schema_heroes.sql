CREATE TABLE if not exists heroes (
  id serial PRIMARY KEY,
  alias VARCHAR(200),
  nombre_real VARCHAR(200),
  estado VARCHAR(50),
  nivel_energia INT,
  created_at TIMESTAMPTZ DEFAULT now()
);

CREATE TABLE if not exists heroe_poderes (
  id serial PRIMARY KEY,
  heroe_id serial REFERENCES heroes(id),
  poder VARCHAR(200)
);