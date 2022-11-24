import 'package:flutter/material.dart';

/// Clase para mantener los colores del aplicativo
///
/// https://medium.com/py-bits/turn-any-color-to-material-color-for-flutter-d8e8e037a837
///
/// https://www.rgbtohex.net/hex-to-rgb/
///
class AppColors {
  static const Color blackCoral = Color(0xFF4F5D75);
  static const Color error = Color(0xFFe63946);
  static const Color mandarin = Color(0xFFEF8354);
  static const Color silver = Color(0xFFBFC0C0);
  static const Color spaceCadet = Color(0xFF2D3142);
  static const Color white = Color(0xFFFFFFFF);

  static const MaterialColor blackCoralMC =
      MaterialColor(0xFF4F5D75, <int, Color>{
    50: Color.fromRGBO(79, 93, 117, .1),
    100: Color.fromRGBO(79, 93, 117, .2),
    200: Color.fromRGBO(79, 93, 117, .3),
    300: Color.fromRGBO(79, 93, 117, .4),
    400: Color.fromRGBO(79, 93, 117, .5),
    500: Color.fromRGBO(79, 93, 117, .6),
    600: Color.fromRGBO(79, 93, 117, .7),
    700: Color.fromRGBO(79, 93, 117, .8),
    800: Color.fromRGBO(79, 93, 117, .9),
    900: Color.fromRGBO(79, 93, 117, 1),
  });

  static const MaterialColor errorMC = MaterialColor(0xFFe63946, <int, Color>{
    50: Color.fromRGBO(230, 57, 70, .1),
    100: Color.fromRGBO(230, 57, 70, .2),
    200: Color.fromRGBO(230, 57, 70, .3),
    300: Color.fromRGBO(230, 57, 70, .4),
    400: Color.fromRGBO(230, 57, 70, .5),
    500: Color.fromRGBO(230, 57, 70, .6),
    600: Color.fromRGBO(230, 57, 70, .7),
    700: Color.fromRGBO(230, 57, 70, .8),
    800: Color.fromRGBO(230, 57, 70, .9),
    900: Color.fromRGBO(230, 57, 70, 1),
  });

  static const MaterialColor mandarinMC =
      MaterialColor(0xFFEF8354, <int, Color>{
    50: Color.fromRGBO(239, 131, 84, .1),
    100: Color.fromRGBO(239, 131, 84, .2),
    200: Color.fromRGBO(239, 131, 84, .3),
    300: Color.fromRGBO(239, 131, 84, .4),
    400: Color.fromRGBO(239, 131, 84, .5),
    500: Color.fromRGBO(239, 131, 84, .6),
    600: Color.fromRGBO(239, 131, 84, .7),
    700: Color.fromRGBO(239, 131, 84, .8),
    800: Color.fromRGBO(239, 131, 84, .9),
    900: Color.fromRGBO(239, 131, 84, 1),
  });

  static const MaterialColor whiteMC = MaterialColor(0xFFFFFFFF, <int, Color>{
    50: Color.fromRGBO(255, 255, 255, .1),
    100: Color.fromRGBO(255, 255, 255, .2),
    200: Color.fromRGBO(255, 255, 255, .3),
    300: Color.fromRGBO(255, 255, 255, .4),
    400: Color.fromRGBO(255, 255, 255, .5),
    500: Color.fromRGBO(255, 255, 255, .6),
    600: Color.fromRGBO(255, 255, 255, .7),
    700: Color.fromRGBO(255, 255, 255, .8),
    800: Color.fromRGBO(255, 255, 255, .9),
    900: Color.fromRGBO(255, 255, 255, 1),
  });

  static const MaterialColor silverMC = MaterialColor(0xFFBFC0C0, <int, Color>{
    50: Color.fromRGBO(191, 192, 192, .1),
    100: Color.fromRGBO(191, 192, 192, .2),
    200: Color.fromRGBO(191, 192, 192, .3),
    300: Color.fromRGBO(191, 192, 192, .4),
    400: Color.fromRGBO(191, 192, 192, .5),
    500: Color.fromRGBO(191, 192, 192, .6),
    600: Color.fromRGBO(191, 192, 192, .7),
    700: Color.fromRGBO(191, 192, 192, .8),
    800: Color.fromRGBO(191, 192, 192, .9),
    900: Color.fromRGBO(191, 192, 192, 1),
  });

  static const MaterialColor spaceCadetMC =
      MaterialColor(0xFF2D3142, <int, Color>{
    50: Color.fromRGBO(45, 49, 66, .1),
    100: Color.fromRGBO(45, 49, 66, .2),
    200: Color.fromRGBO(45, 49, 66, .3),
    300: Color.fromRGBO(45, 49, 66, .4),
    400: Color.fromRGBO(45, 49, 66, .5),
    500: Color.fromRGBO(45, 49, 66, .6),
    600: Color.fromRGBO(45, 49, 66, .7),
    700: Color.fromRGBO(45, 49, 66, .8),
    800: Color.fromRGBO(45, 49, 66, .9),
    900: Color.fromRGBO(45, 49, 66, 1),
  });
}
