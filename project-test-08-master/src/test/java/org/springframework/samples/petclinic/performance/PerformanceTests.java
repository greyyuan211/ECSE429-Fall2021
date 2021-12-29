package org.springframework.samples.petclinic.performance;

import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerformanceTests {

	private static int ownerID = 4;

	static int[] size = new int[] { 10, 100, 1000 };

	// int[] size = new int[] { 200 };

	static int[] petIds = new int[1000];

	final static String baseUrl = "http://localhost:8080/";

	final static String createPet10 = "createPet10-1.csv";

	final static String updatePet10 = "updatePet10-1.csv";

	final static String createPet100 = "createPet100-1.csv";

	final static String updatePet100 = "updatePet100-1.csv";

	final static String createPet1000 = "createPet1000-1.csv";

	final static String updatePet1000 = "updatePet1000-1.csv";

	final static String createOwner10 = "createOwner10-1.csv";

	final static String updateOwner10 = "updateOwner10-1.csv";

	final static String createOwner100 = "createOwner100-1.csv";

	final static String updateOwner100 = "updateOwner100-1.csv";

	final static String createOwner1000 = "createOwner1000-1.csv";

	final static String updateOwner1000 = "updateOwner1000-1.csv";

	public static String[] createPet = new String[] { createPet10, createPet100, createPet1000 };

	public static String[] updatePet = new String[] { updatePet10, updatePet100, updatePet1000 };

	public static String[] createOwner = new String[] { createOwner10, createOwner100, createOwner1000 };

	public static String[] updateOwner = new String[] { updateOwner10, updateOwner100, updateOwner1000 };

	public static int create = 1;

	public static int update = 2;

	public Owner currOwner;

	private static long globalStartTime;

	public static void main(String[] args) throws IOException, InterruptedException {
		if (!Utils.testConnection(baseUrl)) {
			System.out.println("Please make sure the system is running.");
		}
		else {
			testCreatePet();
			testUpdatePet();
			testCreateOwner();
			testUpdateOwner();
		}
	}

	public static void testCreatePet() throws InterruptedException, IOException {
		for (int j = 0; j < 3; j++) {
			FileWriter csvWriter = new FileWriter(createPet[j]);
			csvWriter.append("Transaction time");
			csvWriter.append(",");
			csvWriter.append("CPU Usage");
			csvWriter.append(",");
			csvWriter.append("Memory Usage");
			csvWriter.append("\n");
			// currOwner = ownerRepository.findById(ownerID);
			OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
					.getOperatingSystemMXBean();
			System.out.println("Size: " + size[j]);
			long t1 = 0;
			long t2 = 0;
			double totalCpu = 0.0;
			double totalMemoryUsage = 0.0;
			double averageCpu = 0.0;
			double averageMemoryUsage = 0.0;
			double averageTime = 0.0;

			// start timer
			long totalTimeStart = System.currentTimeMillis();
			for (int i = 0; i < size[j]; i++) {
				long t1_start_time = System.currentTimeMillis();
				double memoryUsage = 0;
				double cpu = 0;
				int petId = CreateUpdatePet(i, create);
				petIds[i] = i;
				long t1_end_time = System.currentTimeMillis();
				t1 = t1_end_time - t1_start_time;
				t2 += t1;
				cpu = (double) ((com.sun.management.OperatingSystemMXBean) operatingSystemMXBean).getProcessCpuLoad();
				memoryUsage = (double) Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				totalCpu += (double) cpu;
				totalMemoryUsage += (double) memoryUsage;
				List<String> result = Arrays.asList(String.valueOf(t1), String.valueOf(cpu * 100.0),
						String.valueOf(memoryUsage));
				csvWriter.append(String.join(",", result));
				csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();
			long totalTimeEnd = System.currentTimeMillis();
			long totalTime = totalTimeEnd - totalTimeStart;
			averageCpu = totalCpu / (double) size[j] * 100.0;
			averageMemoryUsage = totalMemoryUsage / (double) size[j];
			averageTime = (double) t2 / size[j];
			System.out.println("Average time: " + averageTime + " ns Average CPU: " + averageCpu
					+ " % Average memory usage: " + averageMemoryUsage + " bytes");
			ownerID++;

		}

	}

	public static void testUpdatePet() throws InterruptedException, IOException {
		for (int j = 0; j < 3; j++) {
			FileWriter csvWriter = new FileWriter(updatePet[j]);
			csvWriter.append("Transaction time");
			csvWriter.append(",");
			csvWriter.append("CPU Usage");
			csvWriter.append(",");
			csvWriter.append("Memory Usage");
			csvWriter.append("\n");
			OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
					.getOperatingSystemMXBean();
			System.out.println("Size: " + size[j]);
			long t1 = 0;
			long t2 = 0;
			double totalCpu = 0.0;
			double totalMemoryUsage = 0.0;
			double averageCpu = 0.0;
			double averageMemoryUsage = 0.0;
			double averageTime = 0.0;

			// start timer
			long totalTimeStart = System.currentTimeMillis();
			for (int i = 0; i < size[j]; i++) {
				long t1_start_time = System.currentTimeMillis();
				double memoryUsage = 0;
				double cpu = 0;
				int currId = petIds[i];
				CreateUpdatePet(currId, update);
				long t1_end_time = System.currentTimeMillis();
				t1 = t1_end_time - t1_start_time;
				t2 += t1;
				cpu = (double) ((com.sun.management.OperatingSystemMXBean) operatingSystemMXBean).getProcessCpuLoad();
				memoryUsage = (double) Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				totalCpu += (double) cpu;
				totalMemoryUsage += (double) memoryUsage;
				List<String> result = Arrays.asList(String.valueOf(t1), String.valueOf(cpu * 100.0),
						String.valueOf(memoryUsage));
				csvWriter.append(String.join(",", result));
				csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();
			long totalTimeEnd = System.currentTimeMillis();
			long totalTime = totalTimeEnd - totalTimeStart;
			averageCpu = totalCpu / (double) size[j] * 100.0;
			averageMemoryUsage = totalMemoryUsage / (double) size[j];
			averageTime = (double) t2 / size[j];
			System.out.println("Average time: " + averageTime + " ns Average CPU: " + averageCpu
					+ " % Average memory usage: " + averageMemoryUsage + " bytes");
			ownerID++;

		}

	}

	public static void testCreateOwner() throws InterruptedException, IOException {
		for (int j = 0; j < 3; j++) {
			FileWriter csvWriter = new FileWriter(createOwner[j]);
			// "Average time: "+averageTime+" ns Average CPU: "+averageCpu+" % Average
			// memory
			// usage: "+averageMemoryUsage+" bytes");
			csvWriter.append("Transaction time");
			csvWriter.append(",");
			csvWriter.append("CPU Usage");
			csvWriter.append(",");
			csvWriter.append("Memory Usage");
			csvWriter.append("\n");
			// currOwner = ownerRepository.findById(ownerID);
			OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
					.getOperatingSystemMXBean();
			System.out.println("Size: " + size[j]);
			long t1 = 0;
			long t2 = 0;
			double totalCpu = 0.0;
			double totalMemoryUsage = 0.0;
			double averageCpu = 0.0;
			double averageMemoryUsage = 0.0;
			double averageTime = 0.0;

			// start timer
			long totalTimeStart = System.currentTimeMillis();
			for (int i = 0; i < size[j]; i++) {
				long t1_start_time = System.currentTimeMillis();
				double memoryUsage = 0;
				double cpu = 0;
				int petId = CreateUpdateOwner(i, create);
				petIds[i] = i;
				long t1_end_time = System.currentTimeMillis();
				t1 = t1_end_time - t1_start_time;
				t2 += t1;
				cpu = (double) ((com.sun.management.OperatingSystemMXBean) operatingSystemMXBean).getProcessCpuLoad();
				memoryUsage = (double) Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				totalCpu += (double) cpu;
				totalMemoryUsage += (double) memoryUsage;
				List<String> result = Arrays.asList(String.valueOf(t1), String.valueOf(cpu * 100.0),
						String.valueOf(memoryUsage));
				csvWriter.append(String.join(",", result));
				csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();
			long totalTimeEnd = System.currentTimeMillis();
			long totalTime = totalTimeEnd - totalTimeStart;
			averageCpu = totalCpu / (double) size[j] * 100.0;
			averageMemoryUsage = totalMemoryUsage / (double) size[j];
			averageTime = (double) t2 / size[j];
			System.out.println("Average time: " + averageTime + " ns Average CPU: " + averageCpu
					+ " % Average memory usage: " + averageMemoryUsage + " bytes");
			ownerID++;

		}

	}

	public static void testUpdateOwner() throws InterruptedException, IOException {
		for (int j = 0; j < 3; j++) {
			FileWriter csvWriter = new FileWriter(updateOwner[j]);
			csvWriter.append("Transaction time");
			csvWriter.append(",");
			csvWriter.append("CPU Usage");
			csvWriter.append(",");
			csvWriter.append("Memory Usage");
			csvWriter.append("\n");
			OperatingSystemMXBean operatingSystemMXBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
					.getOperatingSystemMXBean();
			System.out.println("Size: " + size[j]);
			long t1 = 0;
			long t2 = 0;
			double totalCpu = 0.0;
			double totalMemoryUsage = 0.0;
			double averageCpu = 0.0;
			double averageMemoryUsage = 0.0;
			double averageTime = 0.0;

			// start timer
			long totalTimeStart = System.currentTimeMillis();
			for (int i = 0; i < size[j]; i++) {
				long t1_start_time = System.currentTimeMillis();
				double memoryUsage = 0;
				double cpu = 0;
				int currId = petIds[i];
				CreateUpdateOwner(currId, update);
				long t1_end_time = System.currentTimeMillis();
				t1 = t1_end_time - t1_start_time;
				t2 += t1;
				cpu = (double) ((com.sun.management.OperatingSystemMXBean) operatingSystemMXBean).getProcessCpuLoad();
				memoryUsage = (double) Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
				totalCpu += (double) cpu;
				totalMemoryUsage += (double) memoryUsage;
				List<String> result = Arrays.asList(String.valueOf(t1), String.valueOf(cpu * 100.0),
						String.valueOf(memoryUsage));
				csvWriter.append(String.join(",", result));
				csvWriter.append("\n");
			}
			csvWriter.flush();
			csvWriter.close();
			long totalTimeEnd = System.currentTimeMillis();
			long totalTime = totalTimeEnd - totalTimeStart;
			averageCpu = totalCpu / (double) size[j] * 100.0;
			averageMemoryUsage = totalMemoryUsage / (double) size[j];
			averageTime = (double) t2 / size[j];
			System.out.println("Average time: " + averageTime + " ns Average CPU: " + averageCpu
					+ " % Average memory usage: " + averageMemoryUsage + " bytes");
			ownerID++;

		}

	}

	public static int CreateUpdatePet(int petId, int type) throws InterruptedException, UnsupportedEncodingException {
		// create pet
		Pet pet = Utils.createPet();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("name", pet.getName());
		params.put("birthDate", pet.getBirthDate().toString());
		params.put("type", pet.getType().getName());
		StringBuilder postData = new StringBuilder();

		for (Map.Entry<String, String> item : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(item.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(item.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		String path = "owners/" + ownerID;
		if (type == create) {
			path += "/pets/new";
		}
		else {
			path = path + "/pets/" + petId + "/edit";
		}

		Utils.sendRequest("POST", baseUrl, path, postDataBytes);
		Utils.sendRequest("GET", baseUrl, "owners/" + ownerID, postDataBytes);

		return petId;
	}

	public static int CreateUpdateOwner(int ownerId, int type)
			throws InterruptedException, UnsupportedEncodingException {
		// create owner
		Owner owner = Utils.createOwner();
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("firstName", owner.getFirstName());
		params.put("lastName", owner.getLastName());
		params.put("address", owner.getAddress());
		params.put("city", owner.getCity());
		params.put("telephone", owner.getTelephone());
		StringBuilder postData = new StringBuilder();

		for (Map.Entry<String, String> item : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(item.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(item.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		String path = "owners/";
		if (type == create) {
			path += "new";
		}
		else {
			path = path + ownerId + "/edit";
		}

		Utils.sendRequest("POST", baseUrl, path, postDataBytes);
		// Utils.sendRequest("GET",baseUrl,"owners/"+ownerID,postDataBytes);

		return ownerId;
	}

}
