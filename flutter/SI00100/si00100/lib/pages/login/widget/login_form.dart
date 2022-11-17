import 'package:flutter/material.dart';
import 'package:localization/localization.dart';
import 'package:si00100/widget/boton_widget.dart';
import 'package:si00100/widget/dialog_widget.dart';
import 'package:si00100/widget/snack_bar_widget.dart';
import 'package:si00100/widget/hx_widget.dart';
import 'package:si00100/widget/input_widget.dart';

import '../../../widget/link_widget.dart';

/// Clase para el formulario de login
///
class LoginForm extends StatefulWidget {
  const LoginForm({Key? key}) : super(key: key);

  @override
  State<LoginForm> createState() => _LoginForm();
}

/// Button de login
///
class _LoginForm extends State<LoginForm> {
  int _formKey = 1;
  static const int _formKeyLogin = 0;
  static const int _formKeyRecobrar = 2;

  @override
  void initState() {
    super.initState();
    _formKey = _formKeyLogin;
  }

  void _onPressedRecuperarClave() {
    setState(() {
      _formKey = _formKeyLogin;
    });

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: SnackBarWidget(
            titulo: 'login_msg_recuperar_0'.i18n(),
            subtitulo: 'login_msg_recuperar_1'.i18n() +
                'login_msg_recuperar_1'.i18n() +
                'login_msg_recuperar_1'.i18n() +
                'login_msg_recuperar_1'.i18n(),
            icono: "images_svg_mail".i18n()),
        behavior: SnackBarBehavior.floating,
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
    );
  }

  void _onPressedLogin() {
    DialogWidget.showError(context, "Usuario y contraseña incorrecta",
        "Le queda 1 intento Le queda 1 intento Le queda 1 Le queda 1 intento Le queda 1 intento Le queda 1");
  }

  _formRecuperar() {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        HxWidget(indice: 4, texto: "login_recuperar_clave".i18n()),
        const SizedBox(height: 10),
        InputWidget(label: 'login_username_label'.i18n()),
        const SizedBox(height: 26),
        BotonWidget(
          onClick: _onPressedRecuperarClave,
          texto: 'Recuperar',
        ),
        const SizedBox(height: 26),
        LinkWidget(
          text: 'event_cancel'.i18n(),
          onClick: () => {
            setState(() {
              _formKey = _formKeyLogin;
            })
          },
        ),
      ],
    );
  }

  _formLogin() {
    return Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          HxWidget(indice: 4, texto: "login_acceso".i18n()),
          const SizedBox(height: 10),
          InputWidget(label: 'login_username_label'.i18n()),
          const SizedBox(height: 26),
          InputWidget(
            label: 'login_password_label'.i18n(),
            tipo: InputWidget.tipoPassword,
          ),
          const SizedBox(height: 26),
          BotonWidget(
            onClick: () => _onPressedLogin(),
            texto: 'login_event_ingresar'.i18n(),
          ),
          const SizedBox(height: 26),
          LinkWidget(
            text: 'login_event_recuperar'.i18n(),
            onClick: () => {
              setState(() {
                _formKey = _formKeyRecobrar;
              })
            },
          ),
        ]);
  }

  @override
  Widget build(BuildContext context) {
    switch (_formKey) {
      case _formKeyLogin:
        return _formLogin();
      case _formKeyRecobrar:
        return _formRecuperar();
      default:
        return _formLogin();
    }
  }
}
