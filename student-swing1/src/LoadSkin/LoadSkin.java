package LoadSkin;

public class LoadSkin {
    public static void LoadMySkin(){
        try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
            System.out.println("加载炫彩皮肤失败！");
        }
    }
}
