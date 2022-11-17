import 'package:flutter/material.dart';

/// Clase para mantener los colores del aplicativo
///
/// https://medium.com/py-bits/turn-any-color-to-material-color-for-flutter-d8e8e037a837
///
class AppColors {
  static Color spaceCadet = const Color(0xFF2D3142);
  static Color blackCoral = const Color(0xFF4F5D75);
  static Color silver = const Color(0xFFBFC0C0);
  static Color mandarin = const Color(0xFFEF8354);
  static Color white = const Color(0xFFFFFFFF);

  static MaterialColor whiteMC = const MaterialColor(0xFFFFFFFF, {
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

  static MaterialColor blackCoralMC = const MaterialColor(0xFF4F5D75, {
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

  static MaterialColor spaceCadetMC = const MaterialColor(0xFF2D3142, {
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

  static MaterialColor mandarinMC = const MaterialColor(0xFFEF8354, {
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

  static MaterialColor silverMC = const MaterialColor(0xFFBFC0C0, {
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
}
