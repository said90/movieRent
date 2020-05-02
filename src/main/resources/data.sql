INSERT INTO public.users (id_user, enabled, password, username) VALUES (1, true, '$2a$10$mRUrTuGAsZJSvVnU59Dr9uOpSrumrTtfyyBsmZDEI6mwenRBgK/qO', 'admin');
INSERT INTO public.users (id_user, enabled, password, username) VALUES (2, true, '$2a$10$Af2qbVQRpCFchv95MVoHauZONPoVWt4NFOvqVZj6xdrCYWVROg7RC', 'user');
INSERT INTO public.roles (id_role, description, name) VALUES (1, 'Administrator', 'ADMIN');
INSERT INTO public.roles (id_role, description, name) VALUES (2, 'User', 'USER');
INSERT INTO public.user_roles (id_user, id_role) VALUES (1, 1);
INSERT INTO public.user_roles (id_user, id_role) VALUES (2, 2);

create table oauth_access_token (
  token_id VARCHAR(256),
  token bytea,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication bytea,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token bytea,
  authentication bytea
);
