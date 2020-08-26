import java.util.Arrays;

public class Sub {
	int i = 0;
	class Analisi
	{
		public String ip;
		public String sm;
		public char classe;
		public int netBit;
		public int subBit;
		public int hostBit;
		public int netIpByte[];
		public int broadIpByte[];
	};
	Analisi indirizzo = new Analisi();
	
	public int[] decon (String IpSm){
		String[] parts = IpSm.split("\\.");
		int [] aux;
		aux = new int[4];
		for (int i = 0; i < 4; i++) {
			try {
				aux[i] = Integer.parseInt(parts[i]);
				if(aux[i] > 255) {
					aux[0] = -2;
					return aux;
				}
			}
			catch(ArrayIndexOutOfBoundsException | NumberFormatException exception) {
			   aux[0] = -1;
			   return aux;
			}
		}
		return aux;
	}
	public String setSm (String sm) {
		indirizzo.sm = sm;
		return indirizzo.sm;
	}
	public String setIp (String ip) {
		indirizzo.ip = ip;
		return indirizzo.ip;
	}
	public char setClasse () {
		if (decon(indirizzo.ip)[0] > 0 && decon(indirizzo.ip)[0] <= 126) {
			indirizzo.classe = 'A';
		}
		else if (decon(indirizzo.ip)[0] >=128 && decon(indirizzo.ip)[0] <= 191) {
			indirizzo.classe = 'B';
		}
		else if (decon(indirizzo.ip)[0] >=192 && decon(indirizzo.ip)[0] <= 223) {
			indirizzo.classe = 'C';
		}
		else if (decon(indirizzo.ip)[0] >=224 && decon(indirizzo.ip)[0] <= 239) {
			indirizzo.classe = 'D';
		}
		else if (decon(indirizzo.ip)[0] >=240 && decon(indirizzo.ip)[0] <= 255) {
			indirizzo.classe = 'E';
		}
		else {
			indirizzo.classe = 'X';
			return 'X';
		}
		return indirizzo.classe;
	}
	public int setByteCrit () {
		int aux = 0;
		for (int i = 3; i >= 0; i--) {
			if (decon(indirizzo.sm)[i] == 128 || decon(indirizzo.sm)[i] == 192 || decon(indirizzo.sm)[i] == 224 || decon(indirizzo.sm)[i] == 240 || decon(indirizzo.sm)[i] == 248 || decon(indirizzo.sm)[i] == 252 || decon(indirizzo.sm)[i] == 254) {
				aux = i;
			}
		}
		return aux;
	}
	public int setCidr() {
		int aux = 0;
		for (i = 0; i < 4; i++) {
			aux += Integer.bitCount(decon(indirizzo.sm)[i]);
		}
		return aux;
	}
	public int setNetBit () {
		switch(indirizzo.classe) {
			case 'A': 
				indirizzo.netBit = 8;
				return 8;			
			case 'B': 
				indirizzo.netBit = 16;
				return 16;
			case 'C': 
				indirizzo.netBit = 24;
				return 24;
			default: return 1;
		}
	}
	public int setSubBit () {
		indirizzo.subBit = setCidr() - indirizzo.netBit;
		return indirizzo.subBit;
	}
	public void getSubBit () {
		if(indirizzo.subBit < 0) {
			System.out.println("5- ERRORE! I bit di sotto rete sono negativi! " +indirizzo.subBit);
			System.exit(0);
		}
		else System.out.println("5- I bit di sotto rete sono " +indirizzo.subBit);
	}
	public int setHostBit () {
		indirizzo.hostBit = 32 - indirizzo.netBit - indirizzo.subBit;
		return indirizzo.hostBit;
	}
	public void getHostBit () {
		if(indirizzo.hostBit <= 0) {
			System.out.println("6- ERRORE! I bit degli host sono negativi! " +indirizzo.subBit);
			System.exit(0);
		}
		else System.out.println("6- I bit degli host sono " +indirizzo.hostBit);
	}
	public long setHostESubPerNet (int aux, int aux2) {
		return (long)(Math.pow(2,aux)) - aux2;
	}
	public int setMn () {
		return 256 - decon(indirizzo.sm)[setByteCrit()];
	}
	public int[] setNetIp () {
		indirizzo.netIpByte = new int[4];
		for (i = 0; i < 4; i++) {
			indirizzo.netIpByte[i] = decon(indirizzo.ip)[i] & decon(indirizzo.sm)[i];
		}	
		return indirizzo.netIpByte;
	}
	public int[] setBroadIp () {
		int smComplement[];
		indirizzo.broadIpByte = new int[4];
		smComplement = new int[4];
		for (i = 0; i < 4; i++) {
			smComplement[i] = ~decon(indirizzo.sm)[i] & 0xff;
			indirizzo.broadIpByte[i] = decon(indirizzo.ip)[i] | smComplement[i];
		}	
		return indirizzo.broadIpByte;
	}
	public void getByte (int[] aux) {
		for (int i = 0; i < 4; i++) {
			System.out.println("Il byte " +(i+1) +" è " +aux[i]);
		}
	}
	public String setRange(int[] NetSubbyte, int importato) {
		int rangeAux[];
		rangeAux = new int[4];
		for (i = 0; i < 3; i++) {
			rangeAux[i] = NetSubbyte[i];
		}
		rangeAux[3] = NetSubbyte[3] + importato;
		return Arrays.toString(rangeAux); 
	}
	public String setBitMap() {
		String aux;
		aux = new String();
		int j = 0;
		for (i = 1; i <= indirizzo.netBit; i++) {
			aux += "n";
			if(i % 8 == 0) {
				aux += ".";
			}
		}
		j = i;
		for (i = j; i <= indirizzo.subBit + indirizzo.netBit; i++) {
			aux += "s";
			if(i % 8 == 0) {
				aux += ".";
			}
		}
		j = i;
		for (i = j; i <= indirizzo.hostBit + indirizzo.subBit + indirizzo.netBit; i++) {
			aux += "h";
			if(i % 8 == 0 && i != 32) {
				aux += ".";
			}
		}
		aux += "";
		return aux;
	}
	public String setBinaryBit (int[] byteIPSM) {
		String aux;
		String aux2;
		aux = new String();
		aux2 = new String();
		for (int j = 0; j <= 3; j++) {
			aux = Integer.toString(byteIPSM[j], 2);
			if(aux.length() < 8 * (1 + j)) {
				for (i =  aux.length(); i < 8; i++) {
					aux = "0" + aux;
				}
			}
			aux2 += aux;
		}
		return aux2;
	}
	public String getBinaryBit (int[] byteIPSM) {
		StringBuffer aux = new StringBuffer();
		aux.append(setBinaryBit(byteIPSM));
		for (i = 8; i < 32; i += 9)
			aux.insert(i,".");
			return aux.toString();
	}
	public int setSubNet () {
		String aux;
		aux = new String();
		aux = setBinaryBit(decon((indirizzo.ip))).substring(indirizzo.netBit, indirizzo.subBit + indirizzo.netBit); 
		return Integer.parseInt(aux,2) + 1;  
	}
}