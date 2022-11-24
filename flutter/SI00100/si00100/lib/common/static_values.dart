class StaticValues {
  const StaticValues._();

  static const apiURL = 'http://localhost:8080/api';
  static const apiVersion = 'v1';

  static const apiURLV1 = '$apiURL/$apiVersion';

  static const apiURLV1Auth = '$apiURLV1/auth';

  static const apiURLV1AuthLogin = '$apiURLV1/auth/login';

  static const apiURLV1AuthRecuperarClave = '$apiURLV1/auth/recuperar-clave';

  static const apiURLV1AuthCambiarClave = '$apiURLV1/auth/cambiar-clave';
}
