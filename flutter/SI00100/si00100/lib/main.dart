import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'package:localization/localization.dart';
import 'package:si00100/common/app_colors.dart';
import 'package:si00100/pages/login/login_page.dart';
import 'package:form_builder_validators/form_builder_validators.dart';
import 'package:si00100/sharePreferences/preferences.dart';

void main() async {
  /// inicializar el paquete de preferencias
  WidgetsFlutterBinding.ensureInitialized();
  await Preferences.init();

  /// inicializar el paquete de localizacion
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.

        /// colores primarios
        primarySwatch: AppColors.spaceCadetMC,
        primaryColor: AppColors.spaceCadetMC[500],
        primaryColorDark: AppColors.spaceCadetMC[700],
        primaryColorLight: AppColors.spaceCadetMC[300],

        /// elevated button
        elevatedButtonTheme: ElevatedButtonThemeData(
          style: ElevatedButton.styleFrom(
            padding: const EdgeInsets.symmetric(horizontal: 30, vertical: 20),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(15),
            ),
          ),
        ),

        /// para decorar los inputs de flutter_form_builder
        inputDecorationTheme: const InputDecorationTheme(
          contentPadding: EdgeInsets.all(16),
          suffixIconColor: AppColors.mandarin,
          labelStyle: TextStyle(
            color: AppColors.spaceCadetMC,
            fontSize: 14,
          ),
          hintStyle: TextStyle(
            color: AppColors.spaceCadetMC,
            fontSize: 14,
          ),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(15.0)),
            borderSide: BorderSide(color: Colors.grey, width: 1.0),
          ),
          enabledBorder: OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(15.0)),
            borderSide: BorderSide(color: AppColors.blackCoralMC, width: 1.0),
          ),
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(15.0)),
            borderSide: BorderSide(color: AppColors.blackCoral, width: 1.0),
          ),
          errorBorder: OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(15.0)),
            borderSide: BorderSide(color: AppColors.error, width: 1.0),
          ),
        ),
      ),

      // Add the localization delegate
      localizationsDelegates: [
        // delegate from flutter_localization
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
        // delegate from localization package.
        LocalJsonLocalization.delegate,
        // delegate from form builder package.
        FormBuilderLocalizations.delegate,
        // delegate from CupertinoAlertDialog
        DefaultCupertinoLocalizations.delegate,
        // delegate text
        DefaultWidgetsLocalizations.delegate,
      ],

      /// TODO generar login page y luego cambiar a dashboard y registros internos.
      //initialRoute: LoginPage.routeName,
      home: const LoginPage(),
    );
  }
}
