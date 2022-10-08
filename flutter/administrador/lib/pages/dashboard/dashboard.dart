import 'package:administrador/common/app_colors.dart';
import 'package:administrador/pages/dashboard/widget/header_widget.dart';
import 'package:administrador/pages/dashboard/widget/notification_card_widget.dart';

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class DashboardPage extends StatefulWidget {
  @override
  _DashboardPageState createState() => _DashboardPageState();
}

class _DashboardPageState extends State<DashboardPage> {
  @override
  Widget build(BuildContext context) {
    return Container(
        margin: EdgeInsets.all(10),
        padding: EdgeInsets.all(10),
        decoration: BoxDecoration(
          color: AppColors.bgColor,
          borderRadius: BorderRadius.circular(30),
        ),
        child: Column(
          children: [
            HeaderWidget(),
            Expanded(
                child: Row(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Expanded(
                  flex: 2,
                  child: Container(
                    child: Column(
                      children: [
                        NotificationCardWidget(),
                        SizedBox(
                          height: 20,
                        ),
                      ],
                    ),
                  ),
                ),
                Expanded(
                  child: Container(),
                ),
              ],
            ))
          ],
        ));
  }
}
