import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:flutter_form_builder/flutter_form_builder.dart';
import 'package:localization/localization.dart';
import 'package:si00100/controller/login_controller.dart';
import 'package:si00100/widget/dialog_widget.dart';
import 'package:si00100/widget/snack_bar_widget.dart';
import 'package:si00100/widget/hx_widget.dart';
import 'package:form_builder_validators/form_builder_validators.dart';

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
  static const int _formKeyCambiarClave = 1;

  /// Formulario de login
  final _formLoginBuilderKey = GlobalKey<FormBuilderState>();
  final _userFieldKey = GlobalKey<FormBuilderFieldState>();
  final _passwordFieldKey = GlobalKey<FormBuilderFieldState>();

  /// Formulario de cambio de clave
  final _formChangePasswordBuilderKey = GlobalKey<FormBuilderState>();
  final _passwordOldFieldKey = GlobalKey<FormBuilderFieldState>();
  final _passwordNewFieldKey = GlobalKey<FormBuilderFieldState>();
  final _passwordConfirmFieldKey = GlobalKey<FormBuilderFieldState>();

  /// Formulario de recuperar clave
  final _formRecoveryPassBuilderKey = GlobalKey<FormBuilderState>();
  final _emailFieldKey = GlobalKey<FormBuilderFieldState>();

  late bool _passwordVisible;

  @override
  void initState() {
    super.initState();
    _formKey = _formKeyLogin;
    _passwordVisible = false;
  }

  /// Evento de recuperar clave
  ///
  void _onPressedRecuperarClave() {
    // TODO implementar recuperar clave
    // SI DA ERROR SE MUESTRA
    //DialogWidget.showError(context, "Error clave", "oportunidades + mensaje del rest");

    // ELSE
    setState(() {
      _formKey = _formKeyLogin;
    });

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: SnackBarWidget(
            titulo: 'login_msg_recuperar_0'.i18n(),
            subtitulo: 'login_msg_recuperar_1'.i18n(),
            icono: "images_svg_mail".i18n()),
        behavior: SnackBarBehavior.floating,
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
    );
  }

  /// Evento de cambiar clave
  ///
  void _onPressedCambiarClave() {
    // TODO si sale error
    DialogWidget.showError(context, "Error clave", "la clave esta medio rara");

    // else
    // vuelve a la pantalla de login
    /*setState(() {
      _formKey = _formKeyLogin;
    });*/
  }

  _onPressedLogin() async {
    debugPrint(_userFieldKey.currentState?.value);
    debugPrint(_passwordFieldKey.currentState?.value);

    /*_userFieldKey.currentState?.reset();
    _userFieldKey.currentState?.setValue(null);

    _passwordFieldKey.currentState?.reset();
    _passwordFieldKey.currentState?.setValue(null);*/

    LoginController l = new LoginController();

    l.login(context, _userFieldKey.currentState?.value,
        _passwordFieldKey.currentState?.value);

    // TODO: validar login con rest
    // Si es invalido
    //DialogWidget.showError(context, "Error clave", "oportunidades + mensaje del rest");

    // Si es correcto pero hay que cambiar la clave
    setState(() {
      _formKey = _formKeyCambiarClave;
    });

    // Si es correcto y no hay que cambiar la clave
    // Navigator.pushNamed(context, '/home');
  }

  /// Formulario de cambio de clave
  ///
  _formCambiarClave() {
    return Column(
      children: [
        HxWidget(indice: 4, texto: "login_recuperar_clave".i18n()),
        const SizedBox(height: 10),
        FormBuilderTextField(
          key: _passwordFieldKey,
          name: 'password_old',
          enabled: true,
          obscureText: !_passwordVisible,
          decoration: InputDecoration(
            labelText: 'login_password_old_label'.i18n(),
            hintText: 'login_password_old_hint'.i18n(),
            suffixIcon: IconButton(
              icon: Icon(
                _passwordVisible ? Icons.visibility : Icons.visibility_off,
              ),
              onPressed: () {
                setState(() {
                  _passwordVisible = !_passwordVisible;
                });
              },
            ),
          ),
          validator: FormBuilderValidators.compose([
            FormBuilderValidators.required(
                errorText: 'validation_required'.i18n()),
          ]),
        ),
        const SizedBox(height: 10),
        FormBuilderTextField(
          key: _passwordNewFieldKey,
          name: 'password_new_fiel',
          enabled: true,
          obscureText: !_passwordVisible,
          decoration: InputDecoration(
            labelText: 'login_password_new_label'.i18n(),
            hintText: 'login_password_new_hint'.i18n(),
            suffixIcon: IconButton(
              icon: Icon(
                _passwordVisible ? Icons.visibility : Icons.visibility_off,
              ),
              onPressed: () {
                setState(() {
                  _passwordVisible = !_passwordVisible;
                });
              },
            ),
          ),
          validator: FormBuilderValidators.compose([
            FormBuilderValidators.required(
                errorText: 'validation_required'.i18n()),
          ]),
        ),
        const SizedBox(height: 10),
        FormBuilderTextField(
          key: _passwordConfirmFieldKey,
          name: 'password_repeat_fiel',
          enabled: true,
          obscureText: !_passwordVisible,
          decoration: InputDecoration(
            labelText: 'login_password_repeat_label'.i18n(),
            hintText: 'login_password_repeat_hint'.i18n(),
            suffixIcon: IconButton(
              icon: Icon(
                _passwordVisible ? Icons.visibility : Icons.visibility_off,
              ),
              onPressed: () {
                setState(() {
                  _passwordVisible = !_passwordVisible;
                });
              },
            ),
          ),
          validator: FormBuilderValidators.compose([
            FormBuilderValidators.required(
                errorText: 'validation_required'.i18n()),
          ]),
        ),
        const SizedBox(height: 10),
        ElevatedButton(
            onPressed: _onPressedCambiarClave,
            child: Text('login_event_cambiar_clave'.i18n())),
        const SizedBox(height: 20),
        TextButton(
          onPressed: () => _onPressedRecuperarClave(),
          child: Text(
            'login_event_recuperar_clave'.i18n(),
          ),
        ),
      ],
    );
  }

  /// Formulario de recuperar clave
  ///
  _formRecuperar() {
    return FormBuilder(
      key: _formRecoveryPassBuilderKey,
      autovalidateMode: AutovalidateMode.onUserInteraction,
      skipDisabled: false,
      child: Column(
        children: [
          HxWidget(indice: 4, texto: "login_recuperar_clave".i18n()),
          const SizedBox(height: 10),
          FormBuilderTextField(
            key: _emailFieldKey,
            name: 'emal_field',
            enabled: true,
            decoration: InputDecoration(
              labelText: 'login_email_label'.i18n(),
              hintText: 'login_email_hint'.i18n(),
            ),
            validator: FormBuilderValidators.compose([
              FormBuilderValidators.required(
                  errorText: 'validation_required'.i18n()),
              FormBuilderValidators.email(errorText: 'validation_email'.i18n()),
            ]),
          ),
          const SizedBox(height: 20),
          ElevatedButton(
              onPressed: _onPressedRecuperarClave,
              child: Text('login_event_recuperar_clave'.i18n())),
          const SizedBox(height: 20),
          TextButton(
              onPressed: () => {
                    setState(() {
                      _formKey = _formKeyLogin;
                    })
                  },
              child: Text(
                'event_cancel'.i18n(),
              )),
        ],
      ),
    );
  }

  /// Formulario de login
  _formLogin() {
    return Column(
      children: [
        HxWidget(indice: 4, texto: "login_acceso".i18n()),
        // Formulario
        FormBuilder(
          key: _formLoginBuilderKey,
          autovalidateMode: AutovalidateMode.onUserInteraction,
          skipDisabled: false,
          child: Column(
            mainAxisSize: MainAxisSize.min,
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              // login_username
              FormBuilderTextField(
                key: _userFieldKey,
                name: 'username',
                enabled: true,
                decoration: InputDecoration(
                  labelText: 'login_username_label'.i18n(),
                  hintText: 'login_username_hint'.i18n(),
                ),
                validator: FormBuilderValidators.compose([
                  FormBuilderValidators.required(
                      errorText: 'validation_required'.i18n()),
                ]),
              ),
              const SizedBox(height: 10),
              FormBuilderTextField(
                key: _passwordFieldKey,
                name: 'password',
                enabled: true,
                obscureText: !_passwordVisible,
                decoration: InputDecoration(
                  labelText: 'login_password_label'.i18n(),
                  hintText: 'login_password_hint'.i18n(),
                  suffixIcon: IconButton(
                    icon: Icon(
                      _passwordVisible
                          ? Icons.visibility
                          : Icons.visibility_off,
                    ),
                    onPressed: () {
                      setState(() {
                        _passwordVisible = !_passwordVisible;
                      });
                    },
                  ),
                ),
                validator: FormBuilderValidators.compose([
                  FormBuilderValidators.required(
                      errorText: 'validation_required'.i18n()),
                ]),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                  onPressed: () => {
                        _formLoginBuilderKey.currentState?.validate() == true
                            ? _onPressedLogin()
                            : {},
                      },
                  child: Text('login_event_ingresar'.i18n())),
            ],
          ),
        ),
        const SizedBox(height: 10),
        TextButton(
          onPressed: () => {
            setState(() {
              _formKey = _formKeyRecobrar;
            })
          },
          child: Text('login_event_recuperar'.i18n()),
        ),
      ],
    );
  }

  /// build formulario de login
  ///
  @override
  Widget build(BuildContext context) {
    switch (_formKey) {
      case _formKeyLogin:
        return _formLogin();
      case _formKeyRecobrar:
        return _formRecuperar();
      case _formKeyCambiarClave:
        return _formCambiarClave();
      default:
        return _formLogin();
    }
  }
}
