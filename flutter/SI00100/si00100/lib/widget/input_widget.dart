import 'package:flutter/material.dart';
import 'package:si00100/common/app_colors.dart';

/// Clase para el widget de input de texto para varios tipos
///
class InputWidget extends StatefulWidget {
  static const int tipoInput = 0;
  static const int tipoInputForm = 3;
  static const int tipoPassword = 1;
  static const int tipoPasswordForm = 2;

  const InputWidget({
    Key? key,
    required this.tipo,
    this.fieldKey,
    this.hintText,
    this.labelText,
    this.helperText,
    this.counterText,
    this.onSaved,
    this.validator,
    this.onFieldSubmitted,
  }) : super(key: key);

  final Key? fieldKey;
  final int tipo;
  final String? hintText;
  final String? labelText;
  final String? helperText;
  final String? counterText;
  final FormFieldSetter<String>? onSaved;
  final FormFieldValidator<String>? validator;
  final ValueChanged<String>? onFieldSubmitted;

  @override
  _InputWidget createState() => _InputWidget();
}

/// Clase para el widget de input de texto para varios tipos
///
class _InputWidget extends State<InputWidget> {
  final bool _obscureText = true;

  /// textfield base
  ///
  TextField buildTextField() {
    return TextField(
      decoration: InputDecoration(
        hintText: widget.hintText,
        counterText: widget.counterText,
        filled: true,
        fillColor: AppColors.spaceCadetMC[50],
        labelStyle: const TextStyle(fontSize: 12),
        contentPadding: const EdgeInsets.only(left: 30),
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.silver),
          borderRadius: BorderRadius.circular(15),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.blackCoralMC),
          borderRadius: BorderRadius.circular(15),
        ),
      ),
    );
  }

  TextFormField buildTextFieldForm() {
    return TextFormField(
      key: widget.fieldKey,
      decoration: InputDecoration(
        hintText: widget.hintText,
        counterText: widget.counterText,
        filled: true,
        fillColor: AppColors.spaceCadetMC[50],
        labelStyle: const TextStyle(fontSize: 12),
        contentPadding: const EdgeInsets.only(left: 30),
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.silver),
          borderRadius: BorderRadius.circular(15),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.blackCoralMC),
          borderRadius: BorderRadius.circular(15),
        ),
      ),
      onSaved: widget.onSaved,
      validator: widget.validator,
      onFieldSubmitted: widget.onFieldSubmitted,
    );
  }

  /// textfield password
  ///
  TextField buildPassword() {
    return TextField(
      obscureText: _obscureText,
      decoration: InputDecoration(
        hintText: widget.hintText,
        counterText: widget.counterText,
        suffixIcon: Icon(
          Icons.visibility_off_outlined,
          color: AppColors.mandarinMC,
        ),
        filled: true,
        fillColor: AppColors.spaceCadetMC[50],
        labelStyle: const TextStyle(fontSize: 12),
        contentPadding: const EdgeInsets.only(left: 30),
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.silver),
          borderRadius: BorderRadius.circular(15),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.blackCoralMC),
          borderRadius: BorderRadius.circular(15),
        ),
      ),
    );
  }

  /// textfield password form
  ///
  TextFormField buildPasswordForm() {
    return TextFormField(
      key: widget.fieldKey,
      obscureText: _obscureText,
      //maxLength: 8,
      onSaved: widget.onSaved,
      validator: widget.validator,
      onFieldSubmitted: widget.onFieldSubmitted,

      decoration: InputDecoration(
        hintText: widget.hintText,
        labelText: widget.labelText,
        helperText: widget.helperText,
        suffixIcon: Icon(
          Icons.visibility_off_outlined,
          color: AppColors.mandarinMC,
        ),
        filled: true,
        fillColor: AppColors.spaceCadetMC[50],
        labelStyle: const TextStyle(fontSize: 12),
        contentPadding: const EdgeInsets.only(left: 30),
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.silver),
          borderRadius: BorderRadius.circular(15),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: BorderSide(color: AppColors.blackCoralMC),
          borderRadius: BorderRadius.circular(15),
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    switch (widget.tipo) {
      case InputWidget.tipoPassword:
        return buildPassword();
      case InputWidget.tipoPasswordForm:
        return buildPasswordForm();
      case InputWidget.tipoInput:
        return buildTextField();
      case InputWidget.tipoInputForm:
        return buildTextFieldForm();
      default:
        return buildTextField();
    }
  }
}
