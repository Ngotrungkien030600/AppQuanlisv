//ten database
	private static String DATABASE_NAME = "studentmanagement";
	//bang mon hoc
	private static String TABLE_SUBJECT = "subject";
	private static String ID_SUBJECT = "idsubjet";
	private static String SUBJECT_TITLE = "subjecttitle";
	private static String CREDITS = "credits";
	private static String TIME = "time";
	private static String PLACE = "place";
	private static int VERSION = 1;
	
	//bang sinh vien
	private static String TABLE_STUDENT = "student";
	private static String ID_STUDENT = "idstudent";
	private static String STUDEN_NAME = "studentname";
	private static String SEX = "sex";
	private static String STUDEN_CODE = "studentcode";
	private static String DATE_OF_BIRTH = "dataofbirth"
	
	
	//tao bang mon hoc
	private String SQLQuery = "CREATE TABLE" + TABLE_SUBJECT +" ( "+ID_SUBJECT+" INTEGER PRIMARY KEY AUTOINCREMENT, "
		+SUBJECT_TITLE+ " TEXT,"
		+CREDITS+"INTEGER, "
		+TIME+"TEXT,"
		+PLACE+"TEXT)";
		
	//tao bang sinh vien
	private String SQLQuery1 = "CREATE TABLE" + TABLE_STUDENT +" ( "+ID_STUDENT+" integer primary key AUTOINCREMENT, "
		+STUDEN_NAME+ " TEXT,"
		+SEX+"TEXT,"
		+STUDEN_CODE+"TEXT,"
		+DATE_OF_BIRTH+"TEXT,"
		+ID_SUBJECT+"INTEGER, FOREIGN KEY ("+ ID_SUBJECT +" ) REFERENCES "+
		TABLE_SUBJECT+"("+ID_SUBJECT+"))";
	
	