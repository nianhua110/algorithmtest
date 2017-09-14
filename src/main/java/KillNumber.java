/*
 * Copyright (c) 2017, CipherGateway and/or its affiliates. All rights  reserved.
 *
 */

import java.math.BigDecimal;
import java.util.*;

/**
 * 题目描述
 * 给定一个十进制的正整数number，选择从里面去掉一部分数字，希望保留下来的数字组成的正整数最大。
 * 输入描述:
 * 输入为两行内容，第一行是正整数number，1 ≤ length(number) ≤ 1000。第二行是希望去掉的数字数量cnt 1 ≤ cnt < length(number)。
 * 输出描述:
 * 输出保留下来的结果。
 *
 * @author kyle
 */
public class KillNumber {

  public static List<Integer> deleteCnt(List<Integer> a, int cnt) {
    if (cnt == 0) {
      return a;
    }
    int len = a.size();
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < a.size(); i++) {
      if (a.get(i) < min) {
        min = a.get(i);
      }
    }
    int lastPost = 0;
    for (int i = 0; i < a.size(); i++) {
      if (a.get(i) == min) {
        if (i + 1 < a.size() && a.get(i) < a.get(i + 1)) {
          a.remove(i);
          break;
        }
        lastPost = i;
      }
    }
    if (a.size() == len) {
      a.remove(lastPost);
    }
    return deleteCnt(a, cnt - 1);
  }

  public static boolean bigger(String a, String b) {
    return new BigDecimal(a).compareTo(new BigDecimal(b)) > 0;
  }

  public static List<Integer> deleteCntDy(LinkedList<Integer> a, int cnt) {
    String[][] table = new String[cnt][a.size()];
    table[0][0] = String.valueOf(a.get(0));
    for (int i = 1; i < a.size(); i++) {
      if (bigger(table[0][i - 1], String.valueOf(a.get(i)))) {
        table[0][i] = String.valueOf(a.get(i));
      } else {
        table[0][i] = table[0][i - 1];
      }

    }
    for (int i = 1; i < cnt; i++) {
      table[i][i] = table[i - 1][i - 1] + a.get(i);
      for (int j = i + 1; j < a.size(); j++) {
        String str = table[i - 1][j - 1] + a.get(j);
        if (bigger(table[i - 1][j], str)) {
          table[i][j] = table[i - 1][j];
        } else {
          table[i][j] = str;
        }
      }
    }
    StringBuilder sb = new StringBuilder(table[cnt - 1][a.size() - 1]);
    List<Integer> b = new LinkedList<>();
    while (sb.length() > 0) {
      if (a.peekFirst().intValue() == (sb.charAt(0) - '0')) {
        a.pollFirst();
        sb.deleteCharAt(0);
      } else {
        b.add(a.pollFirst());
      }
    }

    b.addAll(a);
    return b;
  }

  public static String delectCntChar(String str, int cnt) {
    StringBuilder sb = new StringBuilder(str);
    int i = 1;
    while (cnt > 0 && i < sb.length()) {
      if (sb.charAt(i) > str.charAt(i - 1)) {
        sb.deleteCharAt(i - 1);
        cnt--;
        i--;
      }

      i++;
    }
    while (cnt > 0) {
      cnt--;
      sb.deleteCharAt(sb.length() - 1);
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String str = scanner.next();
    int cnt = scanner.nextInt();
    System.out.println(delectCntChar(str, cnt));
//    LinkedList<Integer> l = new LinkedList<>();
//    for (int i = 0; i < str.length(); i++) {
//      l.add(str.charAt(i) - '0');
//    }
//    LinkedList<Integer> l2 = new LinkedList<>(l);
////    List<Integer> result = deleteCnt(l, cnt);
////    Iterator<Integer> iterator = result.iterator();
////    while (iterator.hasNext()) {
////      System.out.print(iterator.next());
////    }
//
//    List<Integer> result2 = deleteCntDy(l2, cnt);
//    Iterator<Integer> iterator = result2.iterator();
//    while (iterator.hasNext()) {
//      System.out.print(iterator.next());
//    }
  }
}
/*
3976586154020523962300880671964737778724593606571242584991494668986229285835291323002908476867960200646623388326122235670893738530103282358714444924437904136100327756180675360227395260460902778910380553989589407298751034527862336552985549014484926749942542931303831688603585104852008564203001516929722841936724919564733343000912953826068636786812551774440587188100767280174282302956457303029335254090938764250923672658479458507598811673816450969288929290113827748307621358877175736571006952739887939779548366860431383332468412878953844996902652841019937061686417655296026967227612000982875584291377048376306999211221228707788460070158713409718659728103431119166434817373338512809761785488301165503395293541807035591162020062423434932626586243185307679015262113777220009903281336403825118583385492139204588138428488471867382445787993410086452111592394238701919275231300991366935422555313522331690511292885698171474094128521344957015676199943569717927580485837043452276380024935291558140228836214243228630830642917467887388580936030369847411043220784392525039905497819101060634501682297342216064533460246885125563544331638868418600599817585677839872702293506808837233857768959834946570457223254042574751310636663439351859405738979559238912151752022770363192124326770582977470864342639811981424945285509054317594725093333704771525425892536977448774558099513912031929249335511813485530361038456011512333231950629157535474187022558427576429959827617228248127096349661995922866474258677200454707343921520890335690088502868905762549643747730623606272210037416995789903347575260824515005059336642730479433600286042219860911348843692868882853779643596022341040142240583498911108530434620042830154864450415866594889517149989129099504936779315488853114535116242367048736608226922258529292223536058535184165556295253185228964086555807509634471730267134476409888222897156743275715999126142885630646269101431687094527508768523006396477559309937421350963757177038763103363187014950606766700957742860635723443808212040981306078890643775427367958895911801552063584858104712601901889069007150565965944959772721773129266174654757444958309562432073405137936361713886387897935197641505957430379465918604164364752684885208525624041572854752447644446184590097299354818598583012258302066682575957535797692413736949894870127596043615719376142977182492489201324503616334133831635280546960621179524691638616016447576279820090562575530397232452875170559405172705431524416624148466749231402284750597648667168749097071683873086177824265433885082733097511765780705058706402681815990731356320728888375816310784169136365180740571698268959506380816477857024625389353678513601445563713829169720248327493103677687378264936618328872113863659592772651575383342157782559330086121344797934177909196060612756889738365293841664836970773469278084452888509606274814048029016211655550915849212112651415607372791490839199614973696907474501801914621528179966514028780569021423233248723518261825568380128980525163734392705316024478271741899548905957600146267899172678980359871302514704406579784155596636822315983015302887466192338968291484011553754624172924723558299871867941508178404494238467361806492588808969104989527680363061275468825401968658586347459652343570736730031348520575297834681137979834378374942511015332298522458530335220197049790918990387590709170839499354762363487301494638862889398452620814029814708337530224132476360071716748502369067174826772269306936842715388784236352271128854808872696213477414147352309093670998027817111506197729763312068638688037179667943719779024544526299310024444614672371450384123447366258177451445657327943104789437614056731067679935525230777556093000758473207801197058829057338202544863668267434632757769501786227842314230943746669426738340145754183297365101971786029202434911594857614325591070474184420487446852351119491526935973587861932225294809051335954073036156293611099384320211635946895479778476359922128604611149660595885869589491542553548772957350257909980478728466200819194347553064654951935981961305157310245806038753345206731989149282448813621855383352876613042477929514408940414071522190759659199046801703002824049239342030311817518029735443154388225772864434227556357834351525630117964090725160617952207526696078465792313774142193922731987596032289998428515802780320672909957516185030833428782873415646998175800933807221421227713419267562293484108811102339905937964129921607357132265519107704102467900937555891830378946746315266084557640550172690929746484468448583069683493562132073466384223917646022394039154243125608490067989168620834777451419008577021854484993472062274422614630226718819370865349260137117341658437414823857848816373229256603836083443740020840781809736864255426600542515410514486562505810367155631244790306938650811435187403471429123413036969771565312088002290618646997311287918677438565993322362470649532795283830704957221121648384519056751393014199714534658135123156227433731049501816601501185933156605982194817652374783658865373559591240050588877478326674036632128577101207162703412741947371011335897997138961526584677006220566851919465911246252243942202479208623350366136111569384086677330330011566993357846325915149899038584727962711741600722207268639167163733946006660194848653036919236556830797594131749188828298329226956827124586753443904247012473614987977124771499932401159856553706829365780763937723995117031898961381211163888615228865338380951327042287000176104628523460015142692289878809637051717994372110224009569051457734888960424190715170527558544714720410241481103693605839879819002609048820605200803097436832999781339424435455561551311405429198754361593781337760981318445214583228322916704621592549254838038665876567491007786535271729978884086318828751505595346946097735447372725630283711239710927984824777266626747261099123523600146650272289527515749716507551597679508581511196580236622528545522299583557060255168056701923143863341244742891629781837807727469602066479850346150357557385047913360425308975137153772223374295966581234368054018204700500721885770509186204638298910933713420469611064281151721630527180789181197038393214084475658139925888081749027971082687921319184863163472679381652848523151817404362687827993641228927764030919298326411477115789006222396355575395354479612695492105867413275627384063970968226213409953732215144641143100467473303719585292659684018744979260438915191179094608183697701803408085573848229338471257151375684645922828284133940901356712348565230854996426426459631205547931013609919517861280106449946853939335995876650418538001225003939923734784336648135792696037637712119230884149582982162892772689797470864222015025156964050139062428381953889671872392846198272473088564268515858496934872429211259804038332198461041126003315969170877826783411961508447923412378459888141645170477213187339061698559214419592802315579267765321632013701442236607864866647250091600007158623144646518327940236737210404840958865813979868330988579836668038650499692265480482873598459092757861459090677752615789249371546147374885778548366899936641448219122820610463090864052001191681282942277341033521177446507373945781093405803282185054021499020990369887129917152011454486230401025987354768087266036407046784
 */