package com.sqlitedb_copyexternaldb.uaeCompanies;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AppConst {

    public static final String baseUrl = "http://83.111.135.5:8080/uaecompanies/uaecwebservice.asmx/";
    //http://54.187.50.59/uaecompanies/webservice/";
    public static final String DEVICE_TYPE = "2";
    public static final String keyUser = "user";
    public static final String keyCompany = "company";
    //ad
    public static final String keyLoadCompanyDetailFromHome = "companyDetailFromHome";
    public static final String keyLoadCompanyID= "companyID";
    public static final String keyCompanyNumber= "companyNumber";

    public static final String keyList = "list_type";
    public static final String keyReview = "review";
    public static final String keyRating = "rating";
    public static final String keySort = "sort";
    public static final String keyFilter = "filter";
    public static final String keyFav = "fav";
    public static final String keyTotal = "total";

    public static final String keyLogin = "Login";
    public static final String KeySignup = "Signup";
    public static final String KeyFAQ = "FAQ";

    public static final String KeyRegion = "Regions";

    public static final String keyReturn = "return_data";
    public static final String keyProduct = "product";
    public static final String keyPos = "position";
    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";//2017-02-02 20:56:04

    public static final int REQ_LOGIN = 150;
    public static final int REQ_SORT = 151;
    public static final int REQ_FILTER = 152;
    public static final int REQ_LOCATION = 153;
    public static final int REQ_CATEGORY = 154;
    public static final int REQ_CATEGORY_HEALTH = 162;
    public static final int REQ_SEARCH = 155;
    public static final int REQ_REGION = 156;
    public static final int REQ_CONTACT = 157;
    public static final int REQ_RETURN = 158;
    public static final int REQ_CAMERA = 159;
    public static final int REQ_GALLERY = 160;
    public static final int REQ_SETTING = 161;

    public static final String GET_ABOUT_COMPANY = "get_company";
    public static final String GET_LOGIN = "customer_login";
    public static final String GET_SAVE_CUSTOMER = "save_customer";
    public static final String GET_COUNTRY_LIST = "get_countries_list";
    public static final String Get_CITY_LIST = "get_cities_list";
    public static final String GET_DISTRICT_LIST = "get_districts_list";
    public static final String Get_COMPANIES_LIST = "get_companies_list";
    public static final String GET_FORGOT_PASS = "forget_password";
    public static final String GET_CHANGE_PASS = "change_password";
    public static final String GET_UPDATE_USER = "update_customer";
    public static final String GET_ADD_FAVORITES = "Add";
    public static final String GET_DELETE_FAV = "Delete";
    public static final String GET_FAVORITES = "get_favorites";
    public static final String GET_FAQ = "get_faq";
    public static final String GET_COMPANY_RATING = "company_rating";
    public static final String GET_UPLOAD_IMAGE = "upload_image";
    public static final String GET_CATEGORY_LIST = "Categories";

    public static final String GET_REVIEWS_LIST = "Reviews";
    public static final String GET_SUB_CATEGORY_LIST = "Categories_Sub";
    public static final String GET_SUB_CATEGORY_LIST_HEALTH = "Health_Categories_Sub";//AD N
    public static final String GET_SEARCH_LIST = "get_search_list";
    public static final String GET_VIDEOS = "Videos_V2";
    public static final String POST_COMPANY_REVIEW = "post_company_review";
    public static final String POST_FEEDBACK = "post_feedback";
    public static final String GET_CUSTOMER_DETAIL = "get_customer_details";


    public static final String parentId = "parent_id";
    public static final String serviceType = "ServiceType";
    public static final String deviceToken = "device_token";
    public static final String deviceType = "device_type";
    public static final String customerId = "customer_id";
    public static final String Add_or_Delete = "Add_or_Delete";


    public static final String GET_BY_REGION = "Search_By_Region_V3";
    public static final String GET_BY_REGION_HEALTH = "Health_Search_By_Region";
    //public static final String GET_BY_REGION = "Search_By_Region_V2";
    public static final String GET_BY_Categories = "Search_By_Categories_V2";
    public static final String GET_BY_Categories_Health = "Health_Search_By_Categories";
    public static final String GET_COMPANY_DETAILS= "Company_Details";
    public static final String Search_Nearby = "Search_Nearby_V2";
    public static final String Search_NearbyV1 = "Search_Nearby";
    public static final String Search_By_Text = "Search_By_Text_V2";
    public static final String Search_By_Text_Health = "Health_Search_By_Text";//ad n

    public static final String Companies_Feedbacks = "Companies_Feedbacks";
    public static final String Add_To_Favourite = "Add_To_Favourite";
    public static final String Favourites_List = "Favourites_List";
    public static final String Contactus = "Contactus";
    public static final String Update_Profile = "Update_Profile";
    public static final String Change_Profile = "Change_Password";
    public static final String Aboutus = "Aboutus";
    public static final String Terms_And_Conditions = "Terms_And_Conditions";
    public static final String Privacy_Policy = "Privacy_Policy";
    public static final String Forgot_Password = "Forgot_Password";

    public static final String firstName = "firstname";
    public static final String lastName = "lastname";
    public static final String phone = "phone";
    public static final String gender = "gender";

    public static final String appLanguage = "lang";

    public static final String langEn = "en";
    public static final String langAr = "ar";

    public static final String sortRating = "sort_rating";


    public static final String U_Password_Old = "U_Password_Old";
    public static final String U_Password_New = "U_Password_New";


    public static final String merchantId = "merchant_id";
    /*it may be customer id or merchant id*/
    public static final String searchId = "search_id";
    /*it refer to where to search in 1 - customer, 2- merchant and default is customer*/
    public static final String searchIn = "search_in";

    public static final String ratings = "ratings";
    public static final String image = "image";
    public static final String images = "images";
    public static final String searchKey = "search_key";

    public static final String message = "Message";
    public static final String customerName = "Full_Name";
    public static final String customerEmail = "Email";
    public static final String customerPhone = "Phone";
    public static final String contactType = "type";
    public static final String subject = "Subject";

    //About us page responses

    public static final String idParam = "id";
    public static final String aboutUsEn = "about_us";
    public static final String aboutUsAr = "about_us_ar";
    public static final String termsConditionsEn = "terms_conditions";
    public static final String termsConditionsAr = "terms_conditions_ar";
    public static final String policyProceduresEn = "policy_procedures";
    public static final String policyProceduresAr = "policy_procedures_ar";
    public static final String gpsDistance = "gps_distance";
    public static final String address = "address";
    public static final String fb = "fb";
    public static final String tw = "tw";
    public static final String gp = "gp";
    public static final String ig = "ig";



    //city

    public static final String RegionID = "RegionID";
    public static final String Name_en = "Name_en";
    public static final String Name_ar = "Name_ar";
    public static final String Region_Type = "Region_Type";
    public static final String dCreatedDate = "dCreatedDate";
    public static final String vCreatedBy = "vCreatedBy";


    //company by region

    public static final String cityid = "CityID";
    public static final String areaid = "AreaID";
    public static final String customerid = "CustomerID";
    public static final String usrlat = "lat_User";
    public static final String usrlong = "lang_User";



    //company by categories

    public static final String Categoryid = "CategoryID";
    public static final String SubCategoryId = "SubCategoryID";


    //company near by

    public static final String Distance = "Distance";

    // company search by text

    public static final String text = "text";



    //submit rating

    public static final String CompanyNumber = "CompanyNumber";
    public static final String CustomerID = "CustomerID";
    public static final String Feedback_Rating = "Feedback_Rating";
    public static final String Feedback_Message = "Feedback_Message";


    //add to fav
    public static final String UserID = "UserID";
    public static final String U_Email_fav = "U_Email";



    //City and country responses

    public static final String countries = "countires";
    public static final String cities = "cities";
    public static final String countryId = "country_id";
    public static final String countryNameEn = "country_name";
    public static final String countryNameAr = "country_name_ar";
    public static final String cityId = "city_id";
    public static final String cityNameEn = "city_name";
    public static final String cityNameAr = "city_name_ar";
    public static final String districtId = "district_id";
    public static final String districtNameEn = "district_name";
    public static final String districtNameAr = "district_name_ar";

    //login and sign up responses

    public static final String createDate = "create_date";
    public static final String modificationDate = "modification_date";
    public static final String status = "status";
    public static final String pushNotification = "push_notification";
    public static final String activationDate = "activation_date";
    public static final String sinceDate = "since_date";


    public static final String code = "code";
    public static final String error = "CommonError";
    public static final String success = "success";
    public static final String content = "content";
    public static final String Regions = "Regions";

    //companies response

    public static final String products = "products";
    public static final String nameEn = "name";
    public static final String nameAr = "name_ar";
    public static final String latitude = "latitude";
    public static final String longitude = "longitude";
    public static final String mobile = "mobile";
    public static final String url = "url";
    public static final String addressEn = "address_en";
    public static final String shortAddressEn = "short_address";
    public static final String addressAr = "address_ar";
    public static final String shortAddressAr = "short_address_ar";
    public static final String fax = "fax";
    public static final String isFeatured = "is_featured";
    public static final String partnerId = "partner_id";
    public static final String newListing = "new_listing";
    public static final String distance = "distance";
    public static final String mcId = "mc_id";
    public static final String pId = "p_id";
    public static final String rating = "rating";
    public static final String review = "review";
    public static final String logo = "logo";
    public static final String categories = "categories";
    public static final String categoryId = "category_id";
    public static final String CategoryID = "CategoryID";
    public static final String categoryNameEn = "category_name";
    public static final String categoryNameAr = "category_name_ar";
    public static final String descriptionEn = "description";
    public static final String descriptionAr = "description_ar";
    public static final String categoryColor = "color";
    public static final String bannerImage = "banner_image";
    public static final String isFav = "is_fav";
    public static final String customerRating = "customer_rating";
    public static final String offset = "offset";
    public static final String limit = "limit";
    public static final String imageUrl = "image_url";
    public static final String imageName = "image_name";
    public static final String extension = "extension";




    //reviews response

    public static final String date = "date";
    public static final String publish = "publish";
    public static final String merchantNameEn = "merchant_name";
    public static final String pName = "p_name";
    public static final String userImage = "user_image";


    //param signup

    public static final String U_FullName = "U_FullName";
    public static final String U_Email = "U_Email";
    public static final String U_Password = "U_Password";
    public static final String U_DeviceToken = "U_DeviceToken";
    public static final String U_DeviceType = "U_DeviceType";
    public static final String U_Image = "U_Image";

    //param signup

    public static final String email = "U_Email";
    public static final String Email_For_Password = "Email_For_Password";
    public static final String password = "U_Password";


    //param cities

    public static final String ParentID = "ParentID";


    //faq response

    public static final String questionEn = "question";
    public static final String answerEn = "answer";
    public static final String questionAr = "question_ar";
    public static final String answerAr = "answer_ar";

    //video response

    public static final String titleEn = "title";
    public static final String titleAr = "title_ar";
    public static final String video = "video";

    private static final String keyCreatedAt = "key_created_at";

    public static final String TABLE_ABOUT_COMPANY = "about_company";
    public static final String TABLE_DISTRICT = "district";
    public static final String TABLE_CITY = "city";
    public static final String TABLE_FAQ = "faqs";
    public static final String TABLE_VIDEOS = "videos";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_CATEGORY_HEALTH = "category_health";

    //create table COMPANY
    public static final String CREATE_TABLE_COMPANY = "CREATE TABLE " + TABLE_ABOUT_COMPANY
            + "(" + idParam + " INTEGER PRIMARY KEY NOT NULL," + gpsDistance + " INTEGER,"
            + aboutUsEn + " VARCHAR," + aboutUsAr + " VARCHAR,"
            + termsConditionsEn + " VARCHAR," + termsConditionsAr + " VARCHAR,"
            + policyProceduresEn + " VARCHAR," + policyProceduresAr + " VARCHAR,"
            + addressEn + " VARCHAR," + addressAr + " VARCHAR,"
            + phone + " VARCHAR," + email + " VARCHAR,"
            + fb + " VARCHAR," + tw + " VARCHAR," + gp + " VARCHAR,"
            + ig + " VARCHAR," + image + " VARCHAR,"
            + keyCreatedAt + " TIMESTAMP DEFAULT (DateTime('now')))";

    //create table TABLE_DISTRICT
    public static final String CREATE_TABLE_DISTRICT = "CREATE TABLE " + TABLE_DISTRICT
            + "(" + districtId + " INTEGER PRIMARY KEY NOT NULL," + cityId + " INTEGER,"
            + districtNameEn + " VARCHAR," + districtNameAr + " VARCHAR,"
            + keyCreatedAt + " TIMESTAMP DEFAULT (DateTime('now'))," + " FOREIGN KEY (" + cityId + ") REFERENCES "
            + TABLE_CITY + "(" + districtId + ") ON DELETE SET DEFAULT ON UPDATE SET DEFAULT MATCH FULL )";

    //create table CITY
    public static final String CREATE_TABLE_CITY = "CREATE TABLE " + TABLE_CITY
            + "(" + cityId + " INTEGER PRIMARY KEY NOT NULL," + countryId + " INTEGER,"
            + cityNameEn + " VARCHAR," + cityNameAr + " VARCHAR,"
            + keyCreatedAt + " TIMESTAMP DEFAULT (DateTime('now')))";

    //create table FAQ
    public static final String CREATE_TABLE_FAQ = "CREATE TABLE " + TABLE_FAQ
            + "(" + idParam + " INTEGER PRIMARY KEY NOT NULL,"
            + questionEn + " VARCHAR," + questionAr + " VARCHAR,"
            + answerEn + " VARCHAR," + answerAr + " VARCHAR,"
            + keyCreatedAt + " TIMESTAMP DEFAULT (DateTime('now')))";

    //create table VIDEOS
    public static final String CREATE_TABLE_VIDEOS = "CREATE TABLE " + TABLE_VIDEOS
            + "(" + idParam + " INTEGER PRIMARY KEY NOT NULL,"
            + titleEn + " VARCHAR," + titleAr + " VARCHAR,"
            + video + " VARCHAR," + image + " VARCHAR,"
            + keyCreatedAt + " TIMESTAMP DEFAULT (DateTime('now')))";

    //create table CATEGORY
    public static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY
            + "(" + idParam + " INTEGER NOT NULL,"
            + categoryNameEn + " VARCHAR," + categoryNameAr + " VARCHAR," + descriptionEn + " VARCHAR," + descriptionAr + " VARCHAR," + categoryColor + " VARCHAR DEFAULT '000000'," + bannerImage + " VARCHAR,"
            + keyCreatedAt + " TIMESTAMP DEFAULT (DateTime('now')))";



    //create table Health Category

    public static final String CREATE_TABLE_CATEGORY_HEALTH = "CREATE TABLE " + TABLE_CATEGORY_HEALTH
            + "(" + idParam + " INTEGER NOT NULL,"
            + categoryNameEn + " VARCHAR," + categoryNameAr + " VARCHAR," + descriptionEn + " VARCHAR," + descriptionAr + " VARCHAR," + categoryColor + " VARCHAR DEFAULT '000000'," + bannerImage + " VARCHAR,"
            + keyCreatedAt + " TIMESTAMP DEFAULT (DateTime('now')))";



    public static final String isMap = "is_map";
    public static final String isWeb = "is_web";
    public static final String isTerms = "is_terms";
    public static final String link = "link";
    public static final String favId = "favorite_id";
    public static final String totalFavorites = "total_favorites";
    public static final String flashMode = "flash_mode";
    public static final String isSubCat = "is_sub_cat";
    public static final String canShowNotification="can_show_notification";
    public static final String lat_User = "lat_User";
    public static final String lang_User = "lang_User";
    public static final String page_no = "page_no";







    @IntDef({AlertType.NONE, AlertType.SIMPLE, AlertType.SUCCESS, AlertType.ERROR, AlertType.LOGIN_ERROR, AlertType.PERMISSION_ERROR, AlertType.GPS_ERROR, AlertType.LOG_OUT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AlertType {
        int NONE = 0;
        int SUCCESS = 1;
        int ERROR = 2;
        int LOGIN_ERROR = 3;
        int PERMISSION_ERROR = 4;
        int GPS_ERROR = 5;
        int LOG_OUT = 6;
        int SIMPLE = 7;
    }

    @IntDef({SortType.NONE, SortType.DISTANCE, SortType.RATING, SortType.LATEST})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SortType {
        int NONE = 0;
        int DISTANCE = 1;
        int RATING = 2;
        int LATEST = 3;
    }

    @IntDef({ListType.NONE, ListType.CATEGORY, ListType.CITY, ListType.DISTRICT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ListType {
        int NONE = 0;
        int CATEGORY = 1;
        int CITY = 2;
        int DISTRICT = 3;
    }

    @IntDef({PicType.TAKE_PHOTO, PicType.CHOOSE, PicType.REMOVE, PicType.CANCEL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PicType {
        int TAKE_PHOTO = 0;
        int CHOOSE = 1;
        int REMOVE = 2;
        int CANCEL = 3;
    }

}

