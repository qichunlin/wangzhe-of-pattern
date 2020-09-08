threadpoolali 手动创建线程池阿里巴巴推荐使用方式
/**
 * 创建线程池异步调用
 */
private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
private ExecutorService executor = new ThreadPoolExecutor(10, 20, 200L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), namedThreadFactory);

/**
 * 业务处理方法
 */
public static Integer yourmethod(){
    CompletableFuture.supplyAsync(() -> {
        log.info("===task start===");
        try {
            //业务处理
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            //异常处理
            log.error("出现异常结束");
        }
        log.info("===task finish===");
        return 3;
    }, executor);
    return 0;
}





bigdecimaldeal 处理BigDecimal类型的整数和小数(保留两位)
/**
 * 数字处理
 *
 * @param number
 * @return
 */
public static String dealBigDecimalNumber(BigDecimal number){
    if (!"".equals(number) && number != null){
        if (new BigDecimal(number.intValue()).compareTo(number)==0){
            //整数
            return String.valueOf(number.intValue());
        }else {
            //小数
            return String.valueOf(round(number.toString(), 2));
        }
    }
    return "";
}

public static double round(String value, int scale) {
    return new BigDecimal(value).setScale(scale, DEFAULT_ROUNDING_MODE).doubleValue();
}





myredistemplate 自定义的负载均衡处理RestTemplate配置类
/**
 * 负载均衡的RestTemplate
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}




myidsnowflake 自定义的雪花算法模板代码(可以作为一个工具类)
/**
 * 雪花算法核心代码
 *
 * @author chunlin.qi@hand-china.com
 * @version 1.0
 * @description
 * @date 
 */
@Slf4j
@Component
public class IdGeneratorSnowFlake {
    //在需要用到的地方直接注入
    //@Autowired
    //private IdGeneratorSnowFlake idGeneratorSnowFlake;
    
    private long workerId = 0;
    private long datacenterId = 1;

    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    /**
     * post完成之后开始执行
     */
    @PostConstruct
    public void init() {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId:{}", workerId);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("当前机器的workerId获取失败:{}", e);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflakeId() {
        return snowflake.nextId();
    }

    /**
     * 方法重载以便进行重写
     *
     * @param workerId     0
     * @param datacenterId 31
     * @return
     */
    public synchronized long snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    /**
     * 生成的是不的字符，类似子：73a64edf935d49520287739a66f96e06
     *
     * @return
     */
    public String simpleUUID() {
        return IdUtil.simpleUUID();
    }

    /**
     * 生成的UID是著的字符，类似子：b12b6401-6f9c-4351-b2b6-d8afc9ab9272
     *
     * @return
     */
    public String randomUUID() {
        return IdUtil.randomUUID();
    }

    /**
     * 测试获取雪花算法id
     * 
     * @return
     */
    public String getSnowFlake() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i <= 20; i++) {
            threadPool.submit(()->{
                System.out.println(idGeneratorSnowFlake.snowflakeId());
            });
        }
        //关闭线程
        threadPool.shutdown();
        return "hello snowflake";
    }

    public static void main(String[] args) {
        System.out.println(new IdGeneratorSnowFlake().randomUUID());
        System.out.println(new IdGeneratorSnowFlake().simpleUUID());
    }
}




bigdecimalcompare BigDecimal比较判断BigDecimal两个数字大小
BigDecimal bigDecimal = new BigDecimal("666");
BigDecimal bigDecimal2 = new BigDecimal("777");
//a = -1,表示bigDecimal小于bigDecimal2;
//a = 0,表示bigDecimal等于bigDecimal2;
//a = 1,表示bigDecimal大于bigDecimal2;
int a = bigDecimal.compareTo(bigDecimal2);
System.out.println("比较结果:"+ a);



tluinfo 打印耗时信息(info级别)
/**
 * 打印耗时信息
 *
 * @param logger      logger对象
 * @param startTime   开始时间（毫秒）
 * @param currentTime 当前时间，结束时间（毫秒）
 */
public static void info(Logger logger, long startTime, long currentTime) {
    long time = currentTime - startTime;
    if (time < 1000) {
        logger.info("耗时: {}ms", time);
    } else {
        logger.info("耗时: {}s", time / 1000);
    }
}


duf 格式化当前日期(hutool)
//格式化当前日期(hutool) duf
DateUtil.format(new Date(), "yyyy-MM-dd HHmmss");



dealstr 处理字符串插入
/**
 * 处理字符串插入
 *
 * @return
 */
public static synchronized String dealString(String originalStr) {
    if (StringUtils.isEmpty(originalStr)) {
        return "";
    } else {
        StringBuffer sb = new StringBuffer();
        String ss = DateUtil.format(new Date(), "mmss");
        sb.append(originalStr);
        StringBuffer insertOrderNo = sb.insert(8, ss);
        log.info("生成新字符串之后的数据:{}", insertOrderNo);
        return sb.toString();
    }
}



method 创建方法
public static String method(){
    System.out.println("hello");
    return null;
}


beantomap 对象转map集合
public static Map<String, Object> bean2Map(Object obj) {
    if (obj == null) {
        return null;
    }
    Map<String, Object> map = new HashMap<>();
    try {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();

            // 过滤class属性
            if (!key.equals("class")) {
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);

                if (value != null) {
                    map.put(key, value);
                }
            }
        }
    } catch (Exception e) {
        logger.error("transBean2Map Error " + e.getMessage());
    }

    return map;
}





list.split list集合切分
/**
 * 切分列表
 *
 * @param list 原列表
 * @param ct   每一份容量
 * @param <T>
 * @return
 */
public static <T> List<List<T>> split(List<T> list, int ct) {
    List<List<T>> split = new ArrayList<>();
    int st = 0;
    int ed = ct;
    while (st <= list.size()) {
        if (ed < list.size()) {
            split.add(list.subList(st, ed));
        } else {
            split.add(list.subList(st, list.size()));
            break;
        }
        st = ed;
        ed += ct;
    }
    return split;
}



gbtj GsonBuilder转化Json的方法
/**
 * GSON处理对象转化成json
 *
 * @param object
 * @return
 */
public static String toJson(Object object) {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gsonBuilder.disableHtmlEscaping();
    Gson gson = gsonBuilder.create();
    return gson.toJson(object);
}





tls 线程睡眠操作
try {
	Thread.sleep(100);
}catch (InterruptedException e){
	e.printStackTrace();
	break;
}



lna 快速创建List集合
List<?> list = new ArrayList<>();
log.info("当前集合长度为:{}", list.size());
//foreach遍历
list.forEach(item -> {
    //数据处理
    System.out.println(item);
});


mnm 快速创建Map集合
Map<String, String> map = new HashMap<String, String>();
map.put("A", "A");
log.info("当前Map集合长度为:{}", map.size());



ntnr 创建一个线程
Thread thread = new Thread(new Runnable() {
    @Override
    public void run() {
        //业务操作
    }
});
thread.start();
//关闭线程
thread.interrupt();



tmnp 快速创建案例Map集合
Map<Integer, String> map = new HashMap<>(10);
for (int i = 1; i <= 10; i++) {
    map.put(i, "map" + i);
}
//map forEach
map.forEach((k, v) -> {
    System.out.println("key: " + k + ", value:" + v);
});