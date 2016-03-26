



import net.forkforge.jme3_plugins.flac_loader.FLACLoader;

import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
import com.jme3.ui.Picture;

public class FLACLoaderTest extends SimpleApplication{
	public static void main(String[] args) {
		new FLACLoaderTest().start();
	}

	@Override
	public void simpleInitApp() {
		FLACLoader.init(assetManager);
		AudioNode test_flac =new AudioNode(assetManager,"publicdomain.tropicx.flac",DataType.Buffer);
		test_flac.setPositional(false);
		test_flac.setLooping(true);
		test_flac.setVolume(1f);
		rootNode.attachChild(test_flac);
		test_flac.play();
		Picture pc=new Picture("bg");
		pc.setImage(assetManager,"publicdomain.sunsetintheswamp.png",false);
		pc.setHeight(settings.getHeight());
		pc.setWidth(settings.getWidth());
		pc.setPosition(0,0);
		guiNode.attachChild(pc);		
	}
}
