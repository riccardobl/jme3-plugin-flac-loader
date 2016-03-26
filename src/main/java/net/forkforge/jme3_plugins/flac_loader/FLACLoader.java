

package net.forkforge.jme3_plugins.flac_loader;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

import org.kc7bfi.jflac.sound.spi.FlacAudioFileReader;
import org.kc7bfi.jflac.sound.spi.FlacFormatConversionProvider;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioBuffer;
import com.jme3.audio.AudioKey;
import com.jme3.audio.AudioStream;
import com.jme3.util.BufferUtils;

public class FLACLoader implements AssetLoader{
	
	public static AudioBuffer toJMEAudioBuffer(AudioInputStream stream) throws IOException {
		AudioFormat format=stream.getFormat();
		AudioBuffer audio_buffer=new AudioBuffer();
		audio_buffer.setupFormat(format.getChannels(),format.getSampleSizeInBits(),(int)(format.getSampleRate()));
	
		ByteArrayOutputStream baos=new ByteArrayOutputStream();

		byte[] buf=new byte[512];
		int read=0;
		try{
			while((read=stream.read(buf,0,buf.length))>0)baos.write(buf,0,read);
		}catch(EOFException ex){}

		byte[] dataBytes=baos.toByteArray();
		ByteBuffer data=BufferUtils.createByteBuffer(dataBytes.length);
		data.put(dataBytes).flip();

		stream.close();
		
		audio_buffer.updateData(data);
		return audio_buffer;
	}

	public static AudioStream toJMEAudioStream(AudioInputStream stream){
		AudioFormat format=stream.getFormat();
		AudioStream audio_stream=new AudioStream();
		audio_stream.setupFormat(format.getChannels(),format.getSampleSizeInBits(),(int)(format.getSampleRate()));
		audio_stream.updateData(stream,-1);
		return audio_stream;
	}
	

	public Object load(AssetInfo info) throws IOException {
		InputStream in=info.openStream();
		try{
			
			FlacAudioFileReader audio_file_reader=new FlacAudioFileReader();
			AudioInputStream audio_input_stream=audio_file_reader.getAudioInputStream(in);

			
			AudioFormat sourceFormat=audio_input_stream.getFormat();
			AudioFormat targetFormat = new AudioFormat(
							AudioFormat.Encoding.PCM_SIGNED,
							(int)(sourceFormat.getSampleRate()), 
							sourceFormat.getSampleSizeInBits(), 
							sourceFormat.getChannels(),
							sourceFormat.getChannels() * (sourceFormat.getSampleSizeInBits() / 8), 
							(int)(sourceFormat.getSampleRate()), 
							sourceFormat.isBigEndian());
			FlacFormatConversionProvider flacCoverter = new FlacFormatConversionProvider();
			audio_input_stream = flacCoverter.getAudioInputStream(targetFormat, audio_input_stream);

			
			
	        return ((AudioKey)info.getKey()).isStream()
	        	?toJMEAudioStream(audio_input_stream)
	        	:toJMEAudioBuffer(audio_input_stream);
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			in.close();
		}
		return null;
	}



	public static void init(AssetManager assetManager) {
		assetManager.registerLoader(FLACLoader.class,"flac");
	}

}
