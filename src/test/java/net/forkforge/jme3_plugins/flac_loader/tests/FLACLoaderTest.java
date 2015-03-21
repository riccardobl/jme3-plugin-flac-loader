/**
 * @name FLACLoader
 * @version 1.0
 * @author Riccardo Balbo
 * @license
 * 	This is free and unencumbered software released into the public domain.
 * 	Anyone is free to copy, modify, publish, use, compile, sell, or
 * 	distribute this software, either in source code form or as a compiled
 * 	binary, for any purpose, commercial or non-commercial, and by any
 * 	means.
 * 	In jurisdictions that recognize copyright laws, the author or authors
 * 	of this software dedicate any and all copyright interest in the
 * 	software to the public domain. We make this dedication for the benefit
 * 	of the public at large and to the detriment of our heirs and
 * 	successors. We intend this dedication to be an overt act of
 * 	relinquishment in perpetuity of all present and future rights to this
 * 	software under copyright law.
 * 	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * 	EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * 	MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * 	IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * 	OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * 	ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * 	OTHER DEALINGS IN THE SOFTWARE.
 * 	For more information, please refer to <http://unlicense.org>
 */
package net.forkforge.jme3_plugins.flac_loader.tests;

import net.forkforge.jme3_plugins.flac_loader.FLACLoader;

import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioNode;

public class FLACLoaderTest extends SimpleApplication{
	public static void main(String[] args) {
		new FLACLoaderTest().start();
	}

	@Override
	public void simpleInitApp() {
		FLACLoader.init(assetManager);
		AudioNode test_flac =new AudioNode(assetManager,"test.flac",true);
		test_flac.setPositional(false);
		test_flac.setLooping(false);
		test_flac.setVolume(1f);
		rootNode.attachChild(test_flac);
		test_flac.play();
	}
}
