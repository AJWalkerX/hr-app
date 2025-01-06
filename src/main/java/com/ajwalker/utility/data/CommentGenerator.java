package com.ajwalker.utility.data;

import com.ajwalker.entity.Comment;
import com.ajwalker.entity.Company;
import com.ajwalker.entity.PersonalDocument;
import com.ajwalker.entity.User;
import com.ajwalker.repository.CommentRepository;
import com.ajwalker.repository.CompanyRepository;
import com.ajwalker.repository.PersonalDocumentRepository;
import com.ajwalker.repository.UserRepository;
import com.ajwalker.utility.Enum.company.ECompanyState;
import com.ajwalker.utility.Enum.company.ECompanyType;
import com.ajwalker.utility.Enum.company.ERegion;
import com.ajwalker.utility.Enum.user.EPosition;
import com.ajwalker.utility.Enum.user.EUserState;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

public class CommentGenerator {


	
	public static List<PersonalDocument> createPersonalDocument() {
		PersonalDocument personalDocument1 = PersonalDocument.builder()
				.userId(1L).firstName("Ayşe").lastName("Kaya").position(EPosition.MANAGER).build();
		PersonalDocument personalDocument2 =
				PersonalDocument.builder()
						.userId(2L).firstName("Ayşe").lastName("Kaya").position(EPosition.MANAGER).build();
		PersonalDocument personalDocument3 =
				PersonalDocument.builder()
						.userId(3L).firstName("Ayşe").lastName("Kaya").position(EPosition.MANAGER).build();
		PersonalDocument personalDocument4 =
				PersonalDocument.builder()
						.userId(4L).firstName("Ayşe").lastName("Kaya").position(EPosition.MANAGER).build();
		PersonalDocument personalDocument5 =
				PersonalDocument.builder()
						.userId(5L).firstName("Ayşe").lastName("Kaya").position(EPosition.MANAGER).build();

			
				
				return List.of(personalDocument1, personalDocument2, personalDocument3, personalDocument4, personalDocument5);
	}
	
	public static List<Company> createCompany() {
		Company company1 = Company.builder()
				.companyName("DECATHLON")
				.companyMail("decathlon@gmail.com")
				.companyLogo("https://cdn.prod.website-files.com/6113889e45c6e62ebf4ca212/6501f6e9dcee8380e5958f35_62098b127be6ea501b463d94_decathlon-kolay-ik-2.webp")
				.companyType(ECompanyType.FASHION_AND_TEXTILE)
				.region(ERegion.TURKEY)
				.companyState(ECompanyState.ACCEPTED)
				.build();
		Company company2 = Company.builder()
				.companyName("MEDIA NOVA")
				.companyMail("medianovav@gmail.com")
				.companyLogo("https://cdn.prod.website-files.com/611a7bc6101f80bd34a2943b/67504bf1c79917963cadc49b_medianova%20(2)%20-%20semra%20solak-p-500.png")
				.companyType(ECompanyType.MEDIA_AND_ENTERTAINMENT)
				.region(ERegion.TURKEY)
				.companyState(ECompanyState.ACCEPTED)
				.build();
		Company company3 = Company.builder()
				.companyName("ISSD")
				.companyMail("issd@gmail.com")
				.companyLogo("https://cdn.prod.website-files.com/611a7bc6101f80bd34a2943b/6728b2a36c57738ca5a9e631_ISSD-logo%20-%20Deniz%20Demir%20Kahraman%20(1).avif")
				.companyType(ECompanyType.TECHNOLOGY)
				.region(ERegion.TURKEY)
				.companyState(ECompanyState.ACCEPTED)
				.build();
		Company company4 = Company.builder()
				.companyName("TurkNet")
				.companyMail("turknet@gmail.com")
				.companyLogo("https://cdn.prod.website-files.com/611a7bc6101f80bd34a2943b/670fae3d38ef2a203af6dbb9_Logo_Renkli%201%20(1)-p-500.png")
				.companyType(ECompanyType.TECHNOLOGY)
				.companyState(ECompanyState.ACCEPTED)
				.region(ERegion.TURKEY)
				.build();
		Company company5 = Company.builder()
				.companyName("DELTAVANA")
				.companyMail("deltavana@gmail.com")
				.companyLogo("https://cdn.prod.website-files.com/611a7bc6101f80bd34a2943b/61d41e006d43ad50a4abef04_deltavana_logo-siyah.avif")
				.companyType(ECompanyType.ENERGY)
				.companyState(ECompanyState.ACCEPTED)
				.region(ERegion.TURKEY)
				.build();
		
		return List.of(company1, company2, company3, company4, company5);
		
	}
	
	public static List<User> createUser() {
//		User123** kullanici sifresi
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user1 = User.builder()
		                 .companyId(1L)
		                 .email("user1@gmail.com")
		                 .password(passwordEncoder.encode("User123**"))
		                 .avatar("https://cdn.prod.website-files.com/611a7bc6101f80bd34a2943b/67504416b49d5d3c41ee9d82_Semra%20Solak-p-500.png")
				.userState(EUserState.ACTIVE)
				.isFirstLogin(false)

				.build();
		User user2 = User.builder()
		                 .companyId(2L)
		                 .email("user2@gmail.com")
				.password(passwordEncoder.encode("User123**"))
		                 .avatar("https://cdn.prod.website-files.com/611a7bc6101f80bd34a2943b/66f3ddef167ecc29169dc6ad_I3%20-%20FIRAT%20AYDIN-p-500.avif")
				.userState(EUserState.ACTIVE)
				.isFirstLogin(false)

		                 .build();
		User user3 = User.builder()
		                 .companyId(3L)
		                 .email("user3@gmail.com")
				.password(passwordEncoder.encode("User123**"))
		                 .avatar("https://cdn.prod.website-files.com/611a7bc6101f80bd34a2943b/667bf03e84f0cb50547a84eb_ilkizalp-999x1024-p-500.avif")
				.userState(EUserState.ACTIVE)
				.isFirstLogin(false)
		                 .build();
		User user4 = User.builder()
		                 .companyId(4L)
		                 .email("user4@gmail.com")
				.password(passwordEncoder.encode("User123**"))
		                 .avatar("https://cdn.prod.website-files.com/611a7bc6101f80bd34a2943b/6728b5ffef6a822ae0bce15c_deniz.demir%20(1)-p-500.avif")
				.userState(EUserState.ACTIVE)
				.isFirstLogin(false)
		                 .build();
		User user5 = User.builder()
		                 .companyId(5L)
		                 .email("user5@gmail.com")
				.password(passwordEncoder.encode("User123**"))
		                 .avatar("https://cdn.prod.website-files.com/611a7bc6101f80bd34a2943b/670fae2401e2303d8dffefd8_nilay%20savas%CC%A7%20foto1-p-500.avif")
				.userState(EUserState.ACTIVE)
				.isFirstLogin(false)
		                 .build();
		
		
		return List.of(user1, user2, user3, user4, user5);
		
		
	}
	
	public static List<Comment> createComment() {
		Comment comment1 = Comment.builder().userId(1L).companyId(1L).title("Medianova")
		                         .content("Kolay İK manuel süreçlerimizi dijital hale getirerek operasyonel yükümüzü hafifletti ve hataları minimuma indirdi. Bu dönüşüm sayesinde tüm bu süreçler için ayırdığımız zaman kısaldı; İK ekibimiz stratejik çalışmalara daha fazla odaklanma imkanı buldu.")
				.description("Kolay İK ile tanışmadan önce çalışan izinleri, performans takibi ve raporlama gibi süreçlerimizde ciddi bir operasyonel yük söz konusuydu. Çalışan bilgilerinin manuel olarak güncellenmesi ve takip edilmesi hem zaman alıyor hem de hatalara açık bir yapı oluşturuyordu. Bu da ekiplerimizin verimli çalışmasını olumsuz etkiliyor ve operasyonel yükü artırıyordu.\n" + "\n" + "Kolay İK şirketimizde benim işe başlamamdan önce kullanılmaya başlanmıştı. Kullanıcı dostu tasarımı ve insan kaynakları süreçlerini kolaylaştıran özellikleri tercih edilmesinde önemli bir rol oynadı. İzin takibi ve raporlama süreçlerinin hızlı ve şeffaf bir şekilde yönetilebilmesi Kolay İK’nın şirketimize sağladığı en büyük avantajlardan.\n" + "\n" + "Kolay İK’da en çok faydalandığımız özellikler ve uygulamalar arasında izin yönetimi, personel takibi, performans yönetimi ve raporlama süreçleri yer alıyor. İzin taleplerini kolayca yönetip onaylayabiliyoruz, çalışan bilgilerine hızlı erişim sağlıyoruz ve performans süreçlerini düzenli bir şekilde takip edebiliyoruz. Ayrıca aldığımız raporlarla süreçlerimizi optimize ederek daha stratejik adımlar atabiliyoruz.\n" + "\n" + "\"Kolay İK manuel süreçlerimizi dijital hale getirerek operasyonel yükümüzü hafifletti ve hataları minimuma indirdi. Bu dönüşüm sayesinde tüm bu süreçler için ayırdığımız zaman kısaldı; İK ekibimiz stratejik çalışmalara daha fazla odaklanma imkanı buldu.\" \n" + "\n" + "Çalışanlarımız da Kolay İK’yı oldukça pratik buluyor. Özellikle izin taleplerini hızlıca iletebilmeleri ve süreçlerini takip edebilmeleri büyük kolaylık sağlıyor. Mobil uygulama sayesinde çalışanlarımız sisteme her yerden erişebiliyor, bu da kullanımı oldukça pratik hale getiriyor.\n" + "\n" + "Kolay İK’yı insan kaynakları süreçlerini kolaylaştırmak isteyen tüm şirketlere gönül rahatlığıyla tavsiye ederiz. Kullanıcı dostu yapısı ve operasyonel yükü azaltan özellikleriyle işlerin daha verimli bir şekilde yürütülmesine katkı sağlıyor.")
				.build();
		Comment comment2 = Comment.builder().userId(2L).companyId(2L).title("ISSD")
		                          .content("Artık aday başvurularını daha düzenli bir şekilde tutabiliyor ve arşivleme için ek bir çaba harcamadan hangi adayın hangi pozisyona başvurduğunu kolayca takip edebiliyoruz")
		                          .build();
		Comment comment3 = Comment.builder().userId(3L).companyId(3L).title("TurkNet")
		                          .content("Tüm görüşme notlarımızı, referans formlarımızı ve adayların hikayelerini uçtan uca takip etme imkanı bulduk.")
		                          .build();
		Comment comment4 = Comment.builder().userId(4L).companyId(4L).title("İzmir Özel Türk Koleji")
		                          .content("İşe alım süreçlerinde adayların, planlamanın, notların ve arşivin tek noktada birleşmesi iş yükümüzü önemli ölçüde azalttı.")
		                          .build();
		Comment comment5 = Comment.builder().userId(5L).companyId(5L).title("Kordsa")
				.content("İnsan kaynakları süreçlerini iyileştirme arayışında olan Kordsa, Peopleoma ile iş birliğine giderek, yalnızca bir yıl içinde 6 ülkede dijitalleştirilmiş İK süreçlerini hayata geçirdi.")
		                          .build();
		
		return List.of(comment1, comment2, comment3, comment4, comment5);
	}
	
	
}