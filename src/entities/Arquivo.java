package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {
		protected String path;
		protected BufferedReader br;
		protected FileReader fr;
		protected String line;
		protected BufferedWriter bw;
		// CONSTRUTOR DE ARQUIVO COM PATH
		protected Arquivo(String path) {
			this.path = path;
		}
		//INIALIZA LEITORES DE AQRUIVO
		protected void lerArquivo(){
			try {
				fr = new FileReader(this.path);
				br = new BufferedReader(fr);
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		//INCIALIZA GRAVADORES DE ARQUIVO
		protected void gravarArquivo(boolean sobrescrever) {
			try {
				bw = new BufferedWriter(new FileWriter(this.path, !sobrescrever));
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		
		
		
		
		
		
		
		
		
}
