/// class with all the constants used in the app
/// https://medium.com/nerd-for-tech/flutter-defining-constants-the-right-way-321d33185b41
class StaticUrl {
  StaticUrl._();
  static const String urlAPIGestor = 'http://127.0.0.1:8080/';
  static const String urlAPIGestorVersion = 'gs_001_10';
  static const String urlAPIUsuario =
      '$urlAPIGestor$urlAPIGestorVersion/usuario';
  static const String urlAPILogin = '$urlAPIGestor$urlAPIGestorVersion/login';
}
