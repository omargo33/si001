import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

// Clase para tener varios tipos de alertas
class AlertDialog extends StatelessWidget {
  const AlertDialog(
      {Key? key,
      required this.title,
      required this.description,
      required this.type})
      : super(key: key);

  // title
  final String title;

  // description
  final String description;

  // type
  final String type;

  static _error(BuildContext context, String title, String content) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return CupertinoAlertDialog(
          title: Text(title),
          content: Text(content),
          actions: <Widget>[
            CupertinoDialogAction(
              child: const Text('Ok'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  // Alerta de confirmación
  static _confirm(
      BuildContext context, String title, String content, Function onConfirm) {
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return CupertinoAlertDialog(
          title: Text(title),
          content: Text(content),
          actions: <Widget>[
            CupertinoDialogAction(
              child: const Text('Cancelar'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
            CupertinoDialogAction(
              child: const Text('Ok'),
              onPressed: () {
                onConfirm();
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  // Alert yes or not
  static _yesOrNot(
      BuildContext context, String title, String content, Function onConfirm) {
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return CupertinoAlertDialog(
          title: Text(title),
          content: Text(content),
          actions: <Widget>[
            CupertinoDialogAction(
              child: const Text('No'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
            CupertinoDialogAction(
              child: const Text('Si'),
              onPressed: () {
                onConfirm();
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  // Alerta de espera
  wait(BuildContext context, String title, String content) {
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return CupertinoAlertDialog(
          title: Text(title),
          content: Text(content),
        );
      },
    );
  }

  // hide wait dialog
  hideWait(BuildContext context) {
    Navigator.of(context).pop();
  }

  // generate dialog content
  @override
  Widget build(BuildContext context) {
    return Container();
  }
}
