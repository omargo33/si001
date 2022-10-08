import 'package:administrador/common/app_colors.dart';
import 'package:flutter/widgets.dart';

class RecruitmentDataWidget extends StatefulWidget {
  @override
  _RecruitmentDataWidgetState createState() => _RecruitmentDataWidgetState();
}

class _RecruitmentDataWidgetState extends State<RecruitmentDataWidget> {
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        color: AppColors.white,
        borderRadius: BorderRadius.circular(20),
      ),
      padding: EdgeInsets.all(20),
      child: Column (
        children[
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,            
            children: [
              Text('Recruitment Progress', 
                style: TextStyle(
                fontWeight: FontWeight.bold,
                color: AppColors.black,
                fontSize: 22,
                ),
              ),
              Container(
                decoration: BoxDecoration(
                color: AppColors.yellow,
                borderRadius: BorderRadius.circular(100))
              ,
                padding: EdgeInsets.symmetric(
                vertical:10,
                horizontal: 20,
                ),

                child: Text('View All',
                style: TextStyle(
                  fontWeight: FontWeight.bold, color: AppColors.black),
                ),
                )
            ],            
          ),
        ],
      ),

Divider(
  thickness: 0.5,
  color : Colors.grey,
    ),
Table (
  
)

  }
}
