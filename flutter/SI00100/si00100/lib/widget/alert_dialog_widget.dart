import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:localization/localization.dart';
import 'package:si00100/common/app_colors.dart';

// Clase para tener varios tipos de alertas
class AlertDialogWidget extends StatelessWidget {
  const AlertDialogWidget({Key? key}) : super(key: key);

  // Alerta de espera
  wait(BuildContext context) {
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return CupertinoAlertDialog(
          title: Text('alert_wait_title'.i18n()),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              const SizedBox(height: 10),
              const CupertinoActivityIndicator(
                radius: 20,
              ),
              const SizedBox(height: 10),
              Text('alert_wait_subtitle'.i18n()),
            ],
          ),
        );
      },
    );
  }

  // hide wait dialog
  hideWait(BuildContext context) {
    Navigator.of(context).pop();
  }

  // Alerta de error
  error(BuildContext context, String title, String subtitle) {
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return CupertinoAlertDialog(
          title: Text(title),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: [
              const SizedBox(height: 10),
              SvgPicture.asset(
                'images_svg_error'.i18n(),
                height: 50,
                width: 50,
                color: AppColors.error,
              ),
              const SizedBox(height: 10),
              Text.rich(TextSpan(
                text: subtitle,
                style: const TextStyle(
                  color: AppColors.error,
                ),
              )),
            ],
          ),
          actions: [
            CupertinoDialogAction(
              child: Text('alert_cancel_button'.i18n()),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  ///Info dialog
  info(BuildContext context, String title, String subtitle, String icono) {
    return ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Stack(
          clipBehavior: Clip.none,
          children: [
            Container(
              height: 90,
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(25.0),
                color: AppColors.spaceCadetMC[700]!,
              ),
              child: Row(
                children: [
                  const SizedBox(width: 10),
                  SvgPicture.asset(
                    icono,
                    height: 80,
                    width: 80,
                    color: AppColors.whiteMC[700],
                  ),
                  const SizedBox(width: 5),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        const SizedBox(height: 17),
                        Text(
                          title,
                          style: const TextStyle(
                            color: AppColors.white,
                            fontSize: 18,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        Text(
                          subtitle,
                          style: TextStyle(
                            color: AppColors.whiteMC[700],
                            fontSize: 14,
                          ),
                          maxLines: 2,
                          overflow: TextOverflow.ellipsis,
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(width: 10),
                ],
              ),
            ),
          ],
        ),
        behavior: SnackBarBehavior.floating,
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
    );
  }

  // generate dialog content
  @override
  Widget build(BuildContext context) {
    return Container();
  }
}
