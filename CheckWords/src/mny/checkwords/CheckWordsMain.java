package mny.checkwords;

import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import mny.checkwords.model.*;
import mny.checkwords.controller.*;
import mny.checkwords.view.*;
import mny.checkwords.processwords.*;


import java.io.*;
import java.io.*;
import javazoom.jl.player.advanced.*;
import javazoom.jl.player.*;

public class CheckWordsMain {
	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		
		Controller controller = new Controller(model, view);
		controller.processUser();
	}
}
