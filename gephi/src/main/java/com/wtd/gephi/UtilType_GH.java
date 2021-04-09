package com.wtd.gephi;

/**
  * @Author: Hanrb
  * @Version: V1.0 
  * @Create Date: 2014年11月10日上午9:40:42
  * @Description: 线损系统中所涉及到的公共代码类型
  * @Parameters: 
*/
public class UtilType_GH {

	public static final  String volType = "02002";//电压等级类型
//	电压等级以主配网区分，1000kV  到  35kV 主网, 代码为01 --- 08
//	35kV以下为配网， 代码为09 --- 13
	public static final  String volMainBegin = "01";//主网电压等级开始
	public static final  String volMainEnd = "08";//主网电压等级结束
	public static final  String volDistBegin = "09";//配网电压等级开始
	public static final  String volDistEnd = "13";//配网电压等级结束
	
	public static final  String vol1000 = "0203137";//1000kv
	public static final  String vol750 = "0203136";//750kv
	public static final  String vol500 = "0203135";//500kv
	public static final  String vol330 = "0203134";//330kv
	public static final  String vol220 = "0203133";//220kv
	public static final  String vol110 = "0203132";//110kv
	public static final  String vol66 = "0203130";//66kv
	public static final  String vol35 = "0203125";//35kv
	public static final  String vol20 = "0203124";//20kv
	public static final  String vol10 = "0203122";//10kv
	public static final  String vol6 = "0203121";//6kv
	public static final  String volD38 = "0203108";//380v
	public static final  String volD22 = "0203107";//220v
	
	public static final  String stationType = "03011";//变电站类型
	
	public static final  String bdzConnectionType = "04001";//电厂接线方式
	//变电站类型----子类型
	public static final  String bdzCode = "0301101";//变电站代码
	public static final  String kgzCode = "0301102";//开关站代码
	public static final  String cbzCode = "0301103";//串补站代码
	public static final  String fdhjzCode = "0301104";//风电汇集站代码
	public static final  String tjzCode = "0301105";//T接站代码
	public static final  String dcsyzCode = "0301106";//电厂升压站
	public static final  String bdhlytzCode = "0301107";//变电换流一体站
	
	public static final  String plantType = "03010";//发电厂类型
	//发电厂类型----子类型
	public static final  String sdcCode = "0301001";//水电厂类型
	public static final  String csxndcCode = "0301002";//抽水蓄能电厂类型
	public static final  String hdcCode = "0301003";//火电厂类型
	public static final  String hedcCode = "0301004";//核电厂类型
	public static final  String fldcCode = "0301005";//风力发电厂类型
	public static final  String qtdcCode = "0301006";//其他类型
	public static final  String tyndcCode = "0301007";//太阳能类型
	public static final  String tidedcCode = "0301008";//潮汐电厂类型
	
	//表计类型
	public static final  String bjType = "04008";//表计类型
	public static final  String bjXzType = "04009";//表计线制
	public static final  String bjMpedType = "03012";//表计测量点类型
	public static final  String bjGwtType = "02015";//表计关口类型
	public static final  String bjCjType = "13002";//表计采集类型
	
	public static final  String kggAllType = "03005";//开关柜等类型
	public static final  String kbsType = "0300504";//开闭所类型
	public static final  String pdsType = "0300505";//配电室类型
	public static final  String hwgType = "0300506";//环网柜类型
	public static final String kgzType = "0300507";//开关站类型
	public static final String fzxType = "0300508";//分支箱类型
	
	public static final  String pqNodeType = "02018";//pq节点类型
	public static final  String phNodeCode = "0201801";//平衡节点
	public static final  String pqNodeCode = "0201802";//PQ节点
	public static final  String pvNodeCode = "0201803";//PV节点

	public static final  String lineJgType = "04004";//线路结构类型
	public static final  String lineKqType = "04007";//线路跨区类型
	//分段线路类型
	public static final  String fdLineType = "0310912";//分段线路类型
	public static final  String fdJkCode = "0310910";//架空
	public static final  String fdDlCode = "0310911";//电缆
	public static final  String fdHhCode = "0400303";//混合
	//相别类型
	public static final  String xbType = "03008";//相别类型
	public static final  String xbACode = "0300801";//A
	public static final  String xbBCode = "0300802";//B
	public static final  String xbCCode = "0300803";//C
	public static final  String xbABCode = "0300804";//AB
	public static final  String xbACCode = "0300805";//AC
	public static final  String xbBCCode = "0300806";//BC
	public static final  String xbABCCode = "0300807";//ABC
	//相数
	public static final  String xsDXCode = "0302801";//单相
	public static final  String xsSXCode = "0302802";//三相
	
	public static final  String connectionType = "04002";//接线类型
	public static final  String stateType = "02002";//运行状态类型
	public static final  String stateYxCode = "0200201";//运行
	public static final  String stateTyCode = "0200202";//停运
	public static final  String stateCcCode = "0200203";//拆除
	public static final  String stateJcwtyCode = "0200204";//建成未投运
	public static final  String stateTuiyCode = "0200205";//退役
	public static final  String stateGhCode = "0200206";//规划
	
	public static final  String fhflType = "05001";//负荷分类类型
	public static final  String fdjFlType = "02019";//发电机类型分类类型
	public static final  String kgZyType = "03007";//开关作用分类类型
	public static final  String devType = "03003";//设备类型
	//代码
	//label_type = '03003' 
	public static final  String lineCode = "0310901";//线路类型,(具体代码)
	public static final  String byqXsCode = "0310927";//箱式变压器代码
	public static final  String byqZnCode = "0310903";//站内变压器代码
	public static final  String byqZsCode = "0310905";//柱上变压器代码
	public static final  String busCode = "0310908";//母线代码
	public static final  String bjCode = "0300304";//表计代码
	public static final  String segLineCode = "0300348";//分段线路代码
	public static final  String cldrCode = "0310914";//串联电容器代码
	public static final  String cldkCode = "0310915";//串联电抗器代码
	public static final  String bldrCode = "0310916";//并联电容器
	public static final  String bldkCode = "0310917";//并联电抗器代码
	public static final  String zsbldrCode = "0310928";//柱上并联电容器
	public static final  String zscldkCode = "0310929";//柱上串联电抗器
	public static final  String bldkType = "1"; // 电抗   类型区分
	public static final  String bldrType = "0"; // 电容
	public static final  String fdjCode = "0310906";//发电机代码
	public static final  String jzwgbcqCode = "0310919";//静止无功补偿器代码
	public static final  String jlxldkqCode = "0312";//交流线路电抗器代码
	public static final  String wbdzxtCode = "0310907";//外部等值系统代码
	public static final  String loadCode = "0310909";//等值负荷代码
	public static final  String volUserCode = "0300349";//低压用户代码
	public static final  String znDlqCode = "0310920";//站内断路器代码
	public static final  String znFhkgCode = "0310922";//站内负荷开关代码
	public static final  String zsDlqCode = "0310923";//柱上断路器代码
	public static final  String zsFhkgCode = "0310925";//柱上负荷开关代码
	public static final  String znGlkgCode = "0310921";//站内隔离开关代码
	public static final  String zsGlkgCode = "0310924";//柱上隔离开关代码
	public static final  String jkLineCode = "0201101";//架空线代码
	public static final  String dlLineCode = "0201102";//电缆代码
	public static final  String xcKgCode = "0300317";//小车开关代码
	public static final  String xcDzCode = "0300319";//小车刀闸代码
	public static final  String towerCode = "0310913";//杆塔代码 
	public static final  String zlxlCode = "0300312";//直流线路 
	public static final  String pdByqCode = "0300347";//配电变压器
	public static final  String llkgCode = "0300351";//联络开关
	public static final  String dljtCode ="0310931";//电缆接头
	public static final  String dlzdCode ="0202";//电缆终端
	
	public static final  String pdByqGbCode = "0207301";//配电公变
	public static final  String pdByqZbCode = "0207302";//配电专变
	
	
	
	public static final  String areaType = "05002";//区域类型
	public static final  String areaAjCode = "0500201";//A+
	public static final  String areaACode = "0500202";//A
	public static final  String areaBCode = "0500203";//B
	public static final  String areaCCode = "0500204";//C
	public static final  String areaDCode = "0500205";//D
	public static final  String areaECdoe = "0500206";//E
	
	public static final String maxLoadType = "05003";//最大负荷类型
	public static final String pLoadCode = "0500301";//省出现统调最高负荷时刻
	public static final String areaLoadCode = "0500302";//地区出现统调最高负荷时刻
	
	//label_type = '03004'
	public static final  String transType = "0300302";//变压器类型--线损系统对应： 20102*/
	public static final  String byqType = "03023";//变压器类型
	public static final  String byqTwoCode = "0310501";//双卷变压器代码
	public static final  String byqThrCode = "0310502";//三卷变压器代码
	public static final  String byqZooCode = "0310503";//自耦变压器代码
	public static final  String byqFurCode = "0310504";//四卷变压器代码
	public static final  String byqFlbCode = "0310505";//分裂变压器代码
	
	
	public static final  String characterType = "02020";//公用、专用
	public static final  String gyCode = "0202001";//公用
	public static final  String zyCode = "0202002";//专用
	
	//评价结果
	public static final  String pjjgZcType = "1000116";   //评价结果--正常
	public static final  String pjjgLineType = "1000117";   //评价结果--偏大，低压线过长或过小
	public static final  String pjjgLoadType = "1000118";   //评价结果--偏大，负荷过重
	
	//方案名称
	public static final  String caseType = "14001";//方案名称
	public static final  String caseMainType = "1400109";//主网线损方案
	public static final  String caseDisType = "1400110";//配网线损方案
	public static final  String caseVolType = "1400111";//低压线损方案
	
	
	public static final  String modeType = "05003";
	public static final  String modeXjType = "0500301";//新建
	public static final  String modeYyType = "0500302";//已有
	public static final  String modeKjType = "0500303";//扩建
	public static final  String modeGzType = "0500304";//改造
	
	public static final  String kjType = "02016";//口径类型
	public static final  String kjTdCode = "0201601";//统调
	public static final  String kjQkjCode = "0201602";//全口径
	
	public static final  String modeFdCode = "0400501";//丰大
	
	public static final  String SCHENMETYPE = "02022";//变电站户型
	
    //CLS_ID
	public static final  String gdpClsId = "88B5D16A-A031-450C-AC84-52F28A0D9C51-00012";//GDP   CLS_ID
	public static final  String grcPara = "19783858-E171-4C5B-AC34-5DD1D3F35A66-00980"; // 规划--35KV～110KV电网可靠性计算参数 CLS_ID
	public static final  String ouomrInfo = "19783858-E171-4C5B-AC34-5DD1D3F35A66-01013"; //规划--一户一表率表
	public static final  String svprInfo = "4863A416-29FC-4A03-9481-E9198C9945F7-00021"; //规划--综合电压合格率表
	public static final  String smlInfo= "F36F7F8F-5FB1-40B1-BCDC-39B7AD78D3A7-00160"; //规划-变电站最大负荷
	public static final  String byqnzdClsId = "96A27F19-B986-4E0C-94A9-39A1962800C7-00414";//变压器年最大负荷-CLS_ID
	public static final  String byqzdfhskdlClsId = "3A1E676F-DB64-420E-892B-008FC267F78D";//变压器最大负荷时刻电流-CLS_ID
	public static final  String linelossClsId = "372F5779-A05A-4DD2-8680-A2DEDA2BD5D2-00003";//线损统计-CLS_ID
	public static final  String reliabilityClsId = "19783858-E171-4C5B-AC34-5DD1D3F35A66-01029";//供电可靠性统计-CLS_ID
	public static final  String runTypical= "96A27F19-B986-4E0C-94A9-39A1962800C7-00414"; //规划-设备运行数据
	public static final  String maxLoadInfo= "3A1E676F-DB64-420E-892B-008FC267F78D"; //规划-线路最大负荷时刻电流
	public static final  String caseInfoClsId = "96A27F19-B986-4E0C-94A9-39A1962800C7-01048";//方案信息-CLS_ID
	public static final  String ctdSubInfo = "F94237C1-567F-4187-8165-C8B091F65739"; // 典型参数 变电站参数-CLS_ID
	public static final  String ttdPlecInfo = "F38EE598-7738-4FF6-BBD4-8AE27047F881-00008";// 典型参数 线路典型电气参数-CLS_ID
	public static final  String ttdClecInfo = "C2B999F9-9361-4F48-92EF-3F33CE225AED-00017";// 典型参数 典型电缆电气参数-CLS_ID
	public static final  String ttdTpcInfo ="C4E1022B-3426-46AD-85A2-5BC971BBF22B-00005"; // 典型参数 变压器典型参数-CLS_ID
	public static final  String ttdGbcInfo ="0AB6D32B-5180-4ADB-B26D-4210A197D53F-00001"; // 典型参数 典型发电机电气参数-CLS_ID
	public static final  String indupowerClsId = "198255A7-88D9-4011-B884-7065707D229B-00004";//电力供需-各产业用电量统计-CLS_ID

	public static final  String hvdnProInfoClsId = "B661D9F5-1E6F-4490-923B-F79D1A74D07C";//项目清册-新建扩建工程-CLS_ID
	
	//方案类型
	public static final  String dwwjCaseCode = "0600210";//电网网架方案
	public static final  String dlphCaseCode = "0600209";//电量平衡方案
	public static final  String dldlphCaseCode = "0600208";//电力电量平衡方案
	public static final  String fhycCaseCode = "0600207";//负荷预测方案
	public static final  String zlycCaseCode = "0600206";//总量预测方案
	public static final  String gmjjycCaseCode = "0600205";//国民经济预测方案
	public static final  String xmCaseCode = "0600204";//项目方案
	public static final  String yxCaseCode = "0600203";//运行方案
	public static final  String dytyCaseCode = "0600202";//电源退役安排
	public static final  String dyjsCaseCode = "0600201";//电源建设安排
	
	//计算分析方案
	public static final  String flowCalCaseType = "0900101";//潮流计算方案
	public static final  String wgyhCaseType = "0900102";//无功优化方案
	public static final  String jtaqfxCaseType = "0900103";//静态安全分析方案
	public static final  String wdCalCaseType = "0900104";//稳定计算方案
	public static final  String dlshpCalCaseType = "0900105";//短路计算方案
	public static final  String llCalCaseType = "0900106";//理论计算方案
	public static final  String tzfxCaseType = "0900107";//投资分析方案
	public static final  String dwpgCaseType = "0900108";//电网评估方案
	public static final  String zwxsCaseType = "0900109";//主网线损方案
	public static final  String pwxsCaseType = "0900110";//配网线损方案
	public static final  String dyxsCaseType = "0900111";//低压线损方案
	public static final  String kkxCalCaseType = "0900112";//可靠性计算方案
	public static final  String kjfhycCaseType = "0900113";//空间负荷预测方案

	
	//计算类型
	public static final String dlshpType = "ALG_20121207_00007";//短路计算
	public static final String flowType = "ALG_20121207_00003";//潮流计算
	
	//负荷预测算法
	
	public static final String dlshpCalculate = "201304031830133711"; 	 //短路电流法
	public static final String gray_down = "ALG_20121207165439";	 //包络模型_下限	
	public static final String bpaFlowCalculate = "201304031830133710";	 //BPANew	
	public static final String peopleAverageLoad = "ALG_20121207165409";	 //人均用电负荷	
	public static final String maxUseHourLoadForecast = "ALG_20121207165408";	 //最大利用小时法	
	public static final String linearModelForecast = "ALG_20121207165415";	 //历史数据外推法	
	public static final String unitConsumptionMethod = "ALG_20121207165401";	 //产业单耗法	
	public static final String elasticityFactor = "ALG_20121207165402";	 //弹性系数法	
	public static final String annualAverageGrowthRateForecast = "ALG_20121207165403";	 //平均增长法	
	public static final String peopleAverageForecast = "ALG_20121207165404";	 //人均用电法	
	public static final String rankModelLoadForecast = "ALG_20121207165431";	 //指数拟合曲线	
	public static final String linearModelLoadForecast = "ALG_20121207165433";	 //线性拟合曲线	
	public static final String polynomialModelLoadForecast = "ALG_20121207165432";	 //多项式拟合曲线	
	public static final String firstExponentSmooth = "ALG_20121207165434";	 //一次指数平滑	
	public static final String secondExponentSmooth = "ALG_20121207165435";	 //二次指数平滑	
	public static final String thirdExponentSmooth = "ALG_20121207165436";	 //三次指数平滑	
	public static final String gray_up = "ALG_20121207165437";	 //包络模型_上限	
	public static final String timeAndGrayFunction = "ALG_20121207165438";	 //等维新息	
	public static final String calAndSaveLossGrid = "201304031830133711";	 //BPA	
	public static final String calAndSaveLoss10kv = "201304031830133712";	 //等值容量法	
	public static final String calAndSaveLossLowVol = "201304031830133713";	 //等值阻抗法	
	public static final String cal10kVThreeFlowGrid = "201304031830133714";	 //三相潮流法	
	public static final String flowCalculate = "201304031830133780";	 //潮流计算	
	public static final String avgshpCalculate = "201304031830133715";	 //平均电流法	
	
	//任务类型
	public static final String taskTypeDnaecp = "1101801";//规划任务
	public static final String taskTypeLoss = "1101802";//线损任务
	public static final String taskTypeDnesr = "1101803";//能效任务
	public static final String taskTypeDpasr = "1101805";//规划报告任务
	public static final String taskTypeCfsap = "1101806";//碳流任务
	
	//低压电网评价结果
	public static final String LOW_ANA_MNORMAL = "1000401";//正常
	public static final String LOW_ANA_SMALL = "1000402";//偏小，低压线过长或过小
	public static final String LOW_ANA_BIG = "1000403";//偏大，负荷过重
	
	//算法配置
	
	public static final String flowThree10kV = "201304031830133714";//配网三相潮流法算法ID

	public static final String sb_dz_Type = "02030";//站房分类
	
	public static final String GGGJ = "0203201";//高供高计
	public static final String GGDJ = "0203202";//高供低计
	public static final String DGDJ = "0203203";//低供低计
	
	public static final String ZWLLXSJS_TYPE = "ALG_20121207_00031";//主网理论线损计算类型
	public static final String PWLLXLJS_TYPE = "ALG_20121207_00032";//配网理论线损计算类型
	public static final String DYLLXLJS_TYPE = "ALG_20121207_00033";//低压网理论线损计算类型
	
	public static final String VoltageLevel_10="0203122";//10kV电压等级公共编码
	public static final String VoltageLevel_380="0203108";//10kV电压等级公共编码
	
	public static final String mTime_96 = "02039";
	// 短路损耗类型
	public static final String DLSHLX_ZDDLSH = "0201701";// 最大短路损耗
	public static final String DLSHLX_DLSH = "0201702";// 短路损耗
	
	// 正序计算方法
	public static final String ZXJSFF_DLDKBFB = "0205101";// 按短路电抗百分比计算
	public static final String ZXJSFF_SYSJ = "0205102";// 按实验数据计算
}
