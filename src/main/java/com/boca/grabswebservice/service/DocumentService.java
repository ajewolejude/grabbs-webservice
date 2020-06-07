package com.boca.grabswebservice.service;

//import com.amazonaws.AmazonClientException;
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.DeleteObjectRequest;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

//	private AmazonS3 s3client;
//
//	@Value("${amazonProperties.endpointUrl}")
//	private String endpointUrl;
//	@Value("${amazonProperties.bucketName}")
//	private String bucketName;
//	@Value("${amazonProperties.accessKey}")
//	private String accessKey;
//	@Value("${amazonProperties.secretKey}")
//	private String secretKey;
//
//	private Logger logger = LoggerFactory.getLogger(DocumentService.class);
//
//	@PostConstruct
//	private void initializeAmazon() {
//		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
//		this.s3client = new AmazonS3Client(credentials);
//	}
//
//
//	@Autowired
//	private DocumentRepository documentRepository;
//
//	public List<Document> getAll(){
//		return documentRepository.findAll();
//	}
//
//	public Document get(long id){
//		return documentRepository.findById(id).get();
//	}
//
//	public void save(Document document){
//		documentRepository.save(document);
//	}
//	public List<Document> getDocuments(Long owner_id, String document_category){
//		return documentRepository.getDocuments(owner_id, document_category);
//	}
//
//	public void delete (Long id){
//		documentRepository.deleteById(id);
//	}
//
//
//	private String[] document_details = new String[3];
//
//	public String[] uploadFile(MultipartFile incomingFile, String folder) throws IOException {
//
//		String fileUrl = "";
//		try {
//			File file = convertMultiPartToFile(incomingFile);
//			String fileName = generateFileName(incomingFile);
//
//			fileUrl = endpointUrl + "/" + bucketName + "/" + folder + "/" + fileName;
//			uploadFileTos3bucket(fileName, file, folder);
//			document_details[0]= fileName;
//			document_details[1]=incomingFile.getOriginalFilename();
//			document_details[2]=fileUrl;
//			file.delete();
//		} /*catch (Exception e) {
//			e.printStackTrace();
//		}*/
//		catch (AmazonServiceException ase) {
//	          logger.info("Caught an AmazonServiceException from GET requests, rejected reasons:");
//	          logger.info("Error Message:    " + ase.getMessage());
//	          logger.info("HTTP Status Code: " + ase.getStatusCode());
//	          logger.info("AWS Error Code:   " + ase.getErrorCode());
//	          logger.info("Error Type:       " + ase.getErrorType());
//	          logger.info("Request ID:       " + ase.getRequestId());
//
//
//	            } catch (AmazonClientException ace) {
//	              logger.info("Caught an AmazonClientException: ");
//	                logger.info("Error Message: " + ace.getMessage());
//	            } catch (IOException ioe) {
//	              logger.info("IOE Error Message: " + ioe.getMessage());
//
//	            }
//		return document_details;
//
//	}
//
//	public String deleteFileFromS3Bucket(String fileUrl) {
//		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
//		s3client.deleteObject(new DeleteObjectRequest(bucketName + "/", fileName));
//		return "Successfully deleted";
//	}
//
//	private void uploadFileTos3bucket(String fileName, File file, String folder) {
//		s3client.putObject(
//				new PutObjectRequest(bucketName + "/" + folder , fileName, file));
//				//.withCannedAcl(CannedAccessControlList.PublicRead));
//	}
//
//	private String generateFileName(MultipartFile multiPart) {
//		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
//	}
//
//	private File convertMultiPartToFile(MultipartFile file) throws IOException {
//		File convFile = new File(file.getOriginalFilename());
//		FileOutputStream fos = new FileOutputStream(convFile);
//		fos.write(file.getBytes());
//		fos.close();
//		return convFile;
//	}
//
//	@Async
//	public S3Object downloadFile(final String keyName ) throws IOException {
//		byte[] content = null;
//		final S3Object s3Object = s3client.getObject(bucketName , keyName);
//		final S3ObjectInputStream stream = s3Object.getObjectContent();
//
//		FileUtils.copyInputStreamToFile(stream, new File("src/main/resources/uploads/"));
//
//
//		return s3Object;
//	}

}