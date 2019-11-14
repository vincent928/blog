package fun.moblog.blog.common.mybatisPlus.generator;

/**
 * @Author: XuJing
 * @Date: 2019/6/13 10:15
 */
public class GeneratorCondition {
    //数据库链接
    private String url;
    //数据库用户名
    private String username;
    //数据库密码
    private String password;
    //开发者
    private static String author;
    //是否多模块
    private boolean isMultiModule = false;
    //模块名称
    private String modelName;
    //包路径
    private String parentPackage;

    //生成的文件是否覆盖
    private boolean isFileOverride = false;
    //是否生成entity
    private boolean isEntity = true;
    //是否生成dao
    private boolean isMapper = true;
    //是否生成xml
    private boolean isXml = true;
    //是否生成service
    private boolean isService = true;
    //是否生成ServiceImpl
    private boolean isServiceImpl = true;
    //是否生成controller
    private boolean isController = true;
    //父类  date logicDel basic self为空则无父类
    private String baseEntity;
    //自定义父类全路径
    private String selfBaseEntityPath;
    //自定义父类公告字段 注意使用数据库的字段不是java的字段
   private String[] selfBaseEntityCommon;

    //表名前缀
    private String[] tablePre;
    //表名
    private String[] tableName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        GeneratorCondition.author = author;
    }

    public boolean getIsMultiModule() {
        return isMultiModule;
    }

    public void setIsMultiModule(boolean multiModule) {
        isMultiModule = multiModule;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getParentPackage() {
        return parentPackage;
    }

    public void setParentPackage(String parentPackage) {
        this.parentPackage = parentPackage;
    }

    public boolean getIsFileOverride() {
        return isFileOverride;
    }

    public void setIsFileOverride(boolean fileOverride) {
        isFileOverride = fileOverride;
    }

    public boolean getIsEntity() {
        return isEntity;
    }

    public void setIsEntity(boolean entity) {
        isEntity = entity;
    }

    public boolean getIsMapper() {
        return isMapper;
    }

    public void setIsMapper(boolean mapper) {
        isMapper = mapper;
    }

    public boolean getIsXml() {
        return isXml;
    }

    public void setIsXml(boolean xml) {
        isXml = xml;
    }

    public boolean getIsService() {
        return isService;
    }

    public void setIsService(boolean service) {
        isService = service;
    }

    public boolean getIsServiceImpl() {
        return isServiceImpl;
    }

    public void setIsServiceImpl(boolean serviceImpl) {
        isServiceImpl = serviceImpl;
    }

    public boolean getIsController() {
        return isController;
    }

    public void setIsController(boolean controller) {
        isController = controller;
    }

    public String[] getTablePre() {
        return tablePre;
    }

    public void setTablePre(String[] tablePre) {
        this.tablePre = tablePre;
    }

    public String[] getTableName() {
        return tableName;
    }

    public void setTableName(String[] tableName) {
        this.tableName = tableName;
    }

    public String getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(String baseEntity) {
        this.baseEntity = baseEntity;
    }

    public String getSelfBaseEntityPath() {
        return selfBaseEntityPath;
    }

    public void setSelfBaseEntityPath(String selfBaseEntityPath) {
        this.selfBaseEntityPath = selfBaseEntityPath;
    }

    public String[] getSelfBaseEntityCommon() {
        return selfBaseEntityCommon;
    }

    public void setSelfBaseEntityCommon(String[] selfBaseEntityCommon) {
        this.selfBaseEntityCommon = selfBaseEntityCommon;
    }
}
