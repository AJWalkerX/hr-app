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
	
	public static List<Comment> createComment() {
		Comment comment1 = Comment.builder().userId(5L).companyId(5L).title("MAC")
		                         .content("Kolay İK manuel süreçlerimizi dijital hale getirerek operasyonel yükümüzü hafifletti ve hataları minimuma indirdi. Bu dönüşüm sayesinde tüm bu süreçler için ayırdığımız zaman kısaldı; İK ekibimiz stratejik çalışmalara daha fazla odaklanma imkanı buldu.")
				.description("Kolay İK ile tanışmadan önce çalışan izinleri, performans takibi ve raporlama gibi süreçlerimizde ciddi bir operasyonel yük söz konusuydu. Çalışan bilgilerinin manuel olarak güncellenmesi ve takip edilmesi hem zaman alıyor hem de hatalara açık bir yapı oluşturuyordu. Bu da ekiplerimizin verimli çalışmasını olumsuz etkiliyor ve operasyonel yükü artırıyordu.\n" + "\n" + "Kolay İK şirketimizde benim işe başlamamdan önce kullanılmaya başlanmıştı. Kullanıcı dostu tasarımı ve insan kaynakları süreçlerini kolaylaştıran özellikleri tercih edilmesinde önemli bir rol oynadı. İzin takibi ve raporlama süreçlerinin hızlı ve şeffaf bir şekilde yönetilebilmesi Kolay İK’nın şirketimize sağladığı en büyük avantajlardan.\n" + "\n" + "Kolay İK’da en çok faydalandığımız özellikler ve uygulamalar arasında izin yönetimi, personel takibi, performans yönetimi ve raporlama süreçleri yer alıyor. İzin taleplerini kolayca yönetip onaylayabiliyoruz, çalışan bilgilerine hızlı erişim sağlıyoruz ve performans süreçlerini düzenli bir şekilde takip edebiliyoruz. Ayrıca aldığımız raporlarla süreçlerimizi optimize ederek daha stratejik adımlar atabiliyoruz.\n" + "\n" + "\"Kolay İK manuel süreçlerimizi dijital hale getirerek operasyonel yükümüzü hafifletti ve hataları minimuma indirdi. Bu dönüşüm sayesinde tüm bu süreçler için ayırdığımız zaman kısaldı; İK ekibimiz stratejik çalışmalara daha fazla odaklanma imkanı buldu.\" \n" + "\n" + "Çalışanlarımız da Kolay İK’yı oldukça pratik buluyor. Özellikle izin taleplerini hızlıca iletebilmeleri ve süreçlerini takip edebilmeleri büyük kolaylık sağlıyor. Mobil uygulama sayesinde çalışanlarımız sisteme her yerden erişebiliyor, bu da kullanımı oldukça pratik hale getiriyor.\n" + "\n" + "Kolay İK’yı insan kaynakları süreçlerini kolaylaştırmak isteyen tüm şirketlere gönül rahatlığıyla tavsiye ederiz. Kullanıcı dostu yapısı ve operasyonel yükü azaltan özellikleriyle işlerin daha verimli bir şekilde yürütülmesine katkı sağlıyor.")
				.build();
		Comment comment2 = Comment.builder().userId(8L).companyId(3L).title("KORD-SA")
		                          .content("Artık aday başvurularını daha düzenli bir şekilde tutabiliyor ve arşivleme için ek bir çaba harcamadan hangi adayın hangi pozisyona başvurduğunu kolayca takip edebiliyoruz")
		                          .description("Kordsa ve Kolay İK'nın İK Analitiği uygulaması Peopleoma kişiselleştirilmiş insan kaynakları yönetimine ulaşmak için birlikte çalışıyor.\n" + "İnsan kaynakları süreçlerini iyileştirme arayışında olan Kordsa, Peopleoma ile iş birliğine giderek, yalnızca bir yıl içinde 6 ülkede dijitalleştirilmiş İK süreçlerini hayata geçirdi.\n" + "\uD83D\uDCCC Zorluklar\n" + "Lastik ve inşaat güçlendirme ve kompozit pazarlarında global bir oyuncu olan Kordsa, Türkiye, Brezilya, Endonezya, Tayland, İtalya ve ABD’nin de aralarında bulunduğu 6 ülkede 13 tesiste 4900’ü aşkın çalışanıyla faaliyet gösteriyor. \n" + "Bu yüzden Kordsa’nın en büyük zorluklarından biri, farklı ülkelerde kullanılan çeşitli ERP platformları nedeniyle güncel verilere ulaşmada yaşanan güçlüklerdi. Konsolide bir İK raporu hazırlamak yaklaşık 2 ay sürüyordu ve süreç tamamen manuel olarak Excel üzerinden yönetiliyordu. Tüm verilerin farklı ülkelerde farklı formatlarda tutulması, veri temizleme ve haritalama işlemlerini zorunlu kılarken, manuel işlemler verimlilik, zaman yönetimi ve kalite eksikliğine yol açıyordu.\n" + "\uD83D\uDCCC Çözüm\n" + "Kordsa Ekibi, Peopleoma’yı hayata geçirerek, zaman farkı ve kullanılan ERP platformu fark etmeksizin tüm ülkeler için istediği verilere tüm detaylarıyla anında ulaşabilecek ve veri odaklı kararlar alabilecek, çevik çalışma yöntemlerini geliştirecekti. Veri şeffaflığı, herkesin aynı sayfada olmasına yardımcı olacaktı.\n" + "\uD83D\uDCCC Yol haritası\n" + "Öncelikle veri temizliği ve HR-Web Entegrasyonu adımları tamamlandı. Bunu, verimliliğe doğru büyük bir adım olan gösterge panoları izledi. İK ile ilgili tüm rapor ve sunumlar otomatik hale getirildi. Ardından Peopleoma Highlights, Akıllı Organizasyon Şeması, Sihirli Çalışan Kartları ve Bordro Analitikleri sürece dahil edilerek manuel iş yükü ortadan kaldırıldı. Peopleoma Highlights ile herkes ihtiyaç duyduğu yerde öneri, bilgi ve uyarı almaya başladı. Bu uygulama İK kültürünün tüm organizasyonda yaygınlaştırılmasını sağladı.\n" + "İK analitiği projesinin tamamlanması için 2. adım Peopleoma Tahminleri oldu. Turnover, tutundurma oranları, fazla mesai, işten ayrılanlar ve çalışan sayısı gibi tüm önemli insan kaynakları KPI metrikleri için gelecekteki rakamların tahmin edilmesi devreye alındı.\n" + "Kordsa Türkiye’nin entegrasyonu tamamlandıktan sonra Endonezya, Tayland, Brezilya ve ABD entegrasyonları tamamlandı. Peopleoma, her ülke için yerel ERP platformları ile başarılı bir şekilde entegre oldu, verileri temizledi ve İK analitiği platformuna dahil etti.")
		                          .build();
		
		
		return List.of(comment1, comment2);
	}
	
	
}