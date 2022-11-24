import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:localization/localization.dart';

/// Clase para el cajas de dialogo personalizadas
///
/// https://www.youtube.com/watch?v=IyfiOxvT9vs
///
class DialogWidget {
  static void showError(BuildContext context, String title, String message) {
    showDialog(
      context: context,
      builder: (context) {
        return AlertDialog(
          shape: const RoundedRectangleBorder(
              borderRadius: BorderRadius.all(Radius.circular(25.0))),
          contentPadding: const EdgeInsets.only(
              top: 30.0, left: 25.0, right: 25.0, bottom: 0.0),
          content: SizedBox(
              height: 90.0,
              width: 420.0,
              child: Row(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  SvgPicture.asset(
                    'images_svg_error'.i18n(),
                    height: 70,
                    width: 70,
                    color: Colors.red,
                  ),
                  const SizedBox(width: 10),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          title,
                          style: const TextStyle(
                            fontSize: 18,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        const SizedBox(height: 10),
                        Text(
                          message,
                          style: const TextStyle(
                            fontSize: 14,
                          ),
                          maxLines: 2,
                          overflow: TextOverflow.ellipsis,
                        ),
                      ],
                    ),
                  ),
                ],
              )),
          actions: [
            OutlinedButton(
                onPressed: () => {
                      Navigator.of(context).pop(),
                    },
                child: Text(
                  "event_cancel".i18n(),
                ))
          ],
        );
      },
    );
  }
}
