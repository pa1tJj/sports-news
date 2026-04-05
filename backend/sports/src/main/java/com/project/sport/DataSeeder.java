//package com.project.sport;
//
//import java.time.LocalDateTime;
//import java.util.HashSet;
//import java.util.Set;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.github.javafaker.Faker;
//import com.project.sport.entities.CategoriesEntity;
//import com.project.sport.entities.NewsEntity;
//import com.project.sport.entities.RoleEntity;
//import com.project.sport.entities.TagsEntity;
//import com.project.sport.entities.UserEntity;
//import com.project.sport.enums.NewStatus;
//import com.project.sport.repositories.CategoriesRepository;
//import com.project.sport.repositories.NewsRepository;
//import com.project.sport.repositories.RoleRepository;
//import com.project.sport.repositories.TagsRepository;
//import com.project.sport.repositories.UserRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@Component
//public class DataSeeder implements CommandLineRunner{
//	private final UserRepository userRepository;
//	private final RoleRepository roleRepository;
//	private final NewsRepository newsRepository;
//	private final CategoriesRepository categoriesRepository;
//	private final TagsRepository tagsRepository;
//
//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		
//		RoleEntity role1 = new RoleEntity();
//		role1.setCode("ADMIN");
//		role1.setName("Quản trị viên");
//		RoleEntity role2 = new RoleEntity();
//		role2.setCode("USER");
//		role2.setName("Người dùng");
//		roleRepository.save(role1);
//		roleRepository.save(role2);
//		
//		Faker faker = new Faker();
//		
//		UserEntity admin = new UserEntity();
//		admin.setFullname("phan anh tuan");
//		admin.setPhone(faker.phoneNumber().phoneNumber());
//		admin.setPassword("123");
//		admin.setStatus("ACTIVE");
//		admin.setUsername("phan");
//		Set<RoleEntity> roles1 = new HashSet<>();
//		roles1.add(role1);
//		admin.setRoles(roles1);
//		 
//		UserEntity user = new UserEntity();
//		user.setFullname("nguyen van a");
//		user.setPhone(faker.phoneNumber().phoneNumber());
//		user.setPassword("123");
//		user.setStatus("ACTIVE");
//		admin.setUsername("nguyen");
//		Set<RoleEntity> roles2 = new HashSet<>();
//		roles2.add(role2);
//		admin.setRoles(roles2);
//		
//		userRepository.save(admin);
//		userRepository.save(user);
//		
//		CategoriesEntity c1 = new CategoriesEntity();
//		c1.setName("Football");
//		c1.setSlug("football");
//		c1.setDescription("Bóng đá");
//		categoriesRepository.save(c1);
//		
//		CategoriesEntity c2 = new CategoriesEntity();
//		c2.setName("Premier League");
//		c2.setSlug("premier-league");
//		c2.setDescription("Giải bóng đá Ngoại hạng anh");
//		categoriesRepository.save(c2);
//		
//		CategoriesEntity c3 = new CategoriesEntity();
//		c3.setName("Laliga");
//		c3.setSlug("laliga");
//		c3.setDescription("Giải bóng đá Tây Ban Nha");
//		categoriesRepository.save(c3);
//		
//		CategoriesEntity c4 = new CategoriesEntity();
//		c4.setName("UEFA Champion League");
//		c4.setSlug("uefa");
//		c4.setDescription("Giải bóng đá vô địch các câu lạc bộ Châu Âu");
//		categoriesRepository.save(c4);
//		
//		CategoriesEntity c5 = new CategoriesEntity();
//		c5.setName("World Cup");
//		c5.setSlug("worldcup");
//		c5.setDescription("Giải bóng đá vô địch thế giới");
//		categoriesRepository.save(c5);
//		
//		CategoriesEntity c6 = new CategoriesEntity();
//		c6.setName("Ligue 1");
//		c6.setSlug("ligue1");
//		c6.setDescription("Giải bóng đá Pháp");
//		categoriesRepository.save(c6);
//		
//		CategoriesEntity c7 = new CategoriesEntity();
//		c7.setName("Seria");
//		c7.setSlug("seria");
//		c7.setDescription("Giải bóng đá Ý");
//		categoriesRepository.save(c7);
//		
//		TagsEntity t1 = new TagsEntity();
//		t1.setName("Messi");
//		t1.setSlug("messi");
//		tagsRepository.save(t1);
//		
//		TagsEntity t2 = new TagsEntity();
//		t2.setName("Real Madrid");
//		t2.setSlug("real-madrid");
//		tagsRepository.save(t2);
//		
//		TagsEntity t3 = new TagsEntity();
//		t3.setName("Manchester City");
//		t3.setSlug("manchester-city");
//		tagsRepository.save(t3);
//		
//		TagsEntity t4 = new TagsEntity();
//		t4.setName("Champion League");
//		t4.setSlug("champion-league");
//		tagsRepository.save(t4);
//		
//		TagsEntity t5 = new TagsEntity();
//		t5.setName("Mbappe");
//		t5.setSlug("Mbappe");
//		tagsRepository.save(t5);
//		
//		NewsEntity n1 = new NewsEntity();
//		n1.setTitle("Messi lập hat-trick giúp Inter Miami chiến thắng");
//		n1.setSlug("messi-lap-hattrick-inter-miami");
//		n1.setSummary("Messi tỏa sáng rực rỡ với cú hat-trick trong trận đấu sáng nay.");
//		n1.setContent("<h2>Messi tiếp tục phong độ hủy diệt</h2>\r\n"
//				+ "\r\n"
//				+ "<p>Lionel Messi một lần nữa chứng minh đẳng cấp siêu sao khi ghi ba bàn thắng \r\n"
//				+ "giúp Inter Miami giành chiến thắng thuyết phục 4-1 trước đối thủ trong trận đấu sáng nay.</p>\r\n"
//				+ "\r\n"
//				+ "<img src=\"/images/messi1.jpg\" alt=\"Messi ghi bàn cho Inter Miami\" />\r\n"
//				+ "\r\n"
//				+ "<p>Ngay từ phút thứ 12, Messi đã mở tỷ số bằng một cú sút phạt đẳng cấp. \r\n"
//				+ "Đến hiệp hai, anh tiếp tục ghi thêm hai bàn thắng sau những pha phối hợp \r\n"
//				+ "nhanh và chính xác cùng đồng đội.</p>\r\n"
//				+ "\r\n"
//				+ "<h3>Thống kê nổi bật</h3>\r\n"
//				+ "<ul>\r\n"
//				+ "    <li>3 bàn thắng</li>\r\n"
//				+ "    <li>5 cú sút trúng đích</li>\r\n"
//				+ "    <li>87% tỷ lệ chuyền chính xác</li>\r\n"
//				+ "</ul>\r\n"
//				+ "\r\n"
//				+ "<p>Chiến thắng này giúp Inter Miami củng cố vị trí trong top đầu bảng xếp hạng \r\n"
//				+ "và tiếp tục nuôi hy vọng cạnh tranh danh hiệu mùa giải năm nay.</p>\r\n"
//				+ "\r\n"
//				+ "<p><strong>HLV trưởng phát biểu:</strong> “Messi là nguồn cảm hứng cho toàn đội. \r\n"
//				+ "Cậu ấy không chỉ ghi bàn mà còn tạo ra sự khác biệt trong lối chơi.”</p>\r\n"
//				+ "");
//		n1.setViews(1250);
//		n1.setStatus(NewStatus.PUBLISHED.name());
//		n1.setIsFeatured(true);
//		n1.setPriority(10);
//		n1.setUser(admin);
//		LocalDateTime date1 = LocalDateTime.now();
//		n1.setCreatedDate(date1);
//		Set<CategoriesEntity> s1 = new HashSet<>();
//		s1.add(c1);
//		n1.setCategories(s1);
//		Set<TagsEntity> tag1 = new HashSet<TagsEntity>();
//		tag1.add(t1);
//		tag1.add(t4);
//		n1.setTags(tag1);
//		newsRepository.save(n1);
//		
//		NewsEntity n2 = new NewsEntity();
//		n2.setTitle("Real Madrid chuẩn bị chiêu mộ Mbappe");
//		n2.setSlug("real-madrid-chieu-mo-mbappe");
//		n2.setSummary("Đội bóng hoàng gia Tây Ban Nha sắp hoàn tất thương vụ bom tấn.");
//		n2.setContent("<h2>Thương vụ bom tấn sắp hoàn tất</h2>\r\n"
//				+ "\r\n"
//				+ "<p>Real Madrid được cho là đang tiến rất gần đến việc ký hợp đồng với \r\n"
//				+ "Kylian Mbappe trong kỳ chuyển nhượng mùa hè này.</p>\r\n"
//				+ "\r\n"
//				+ "<img src=\"/images/mbappe.jpg\" alt=\"Mbappe trong màu áo PSG\" />\r\n"
//				+ "\r\n"
//				+ "<p>Theo nhiều nguồn tin uy tín, đội bóng Hoàng gia Tây Ban Nha đã đạt được \r\n"
//				+ "thỏa thuận cá nhân với tiền đạo người Pháp. Mức phí chuyển nhượng có thể \r\n"
//				+ "lên đến 150 triệu euro.</p>\r\n"
//				+ "\r\n"
//				+ "<h3>Lý do Real Madrid quyết tâm chiêu mộ</h3>\r\n"
//				+ "<ol>\r\n"
//				+ "    <li>Tăng cường sức mạnh hàng công</li>\r\n"
//				+ "    <li>Xây dựng đội hình trẻ trung lâu dài</li>\r\n"
//				+ "    <li>Gia tăng sức hút thương mại toàn cầu</li>\r\n"
//				+ "</ol>\r\n"
//				+ "\r\n"
//				+ "<p>Nếu thương vụ hoàn tất, Mbappe sẽ trở thành bản hợp đồng đắt giá nhất \r\n"
//				+ "trong lịch sử câu lạc bộ.</p>\r\n"
//				+ "\r\n"
//				+ "<p>Người hâm mộ đang rất chờ đợi thông báo chính thức từ ban lãnh đạo Real Madrid.</p>\r\n"
//				);
//		n2.setViews(13250);
//		n2.setStatus(NewStatus.PUBLISHED.name());
//		n2.setIsFeatured(false);
//		n2.setPriority(5);
//		n2.setUser(admin);
//		n2.setCreatedDate(date1.plusDays(1));
//		Set<CategoriesEntity> s2 = new HashSet<>();
//		s2.add(c1);
//		s2.add(c3);
//		n2.setCategories(s2);
//		Set<TagsEntity> tag2 = new HashSet<TagsEntity>();
//		tag2.add(t2);
//		tag2.add(t5);
//		n2.setTags(tag2);
//		newsRepository.save(n2);
//		
//		NewsEntity n3 = new NewsEntity();
//		n3.setTitle("Manchester City tiến gần chức vô địch Premier League");
//		n3.setSlug("manchester-city-gan-vo-dich");
//		n3.setSummary("Thầy trò Pep Guardiola duy trì phong độ ổn định.");
//		n3.setContent("<h2>Phong độ ổn định của thầy trò Pep Guardiola</h2>\r\n"
//				+ "\r\n"
//				+ "<p>Manchester City tiếp tục duy trì phong độ ấn tượng khi đánh bại đối thủ \r\n"
//				+ "trực tiếp với tỷ số 2-0, qua đó tiến gần hơn tới chức vô địch Premier League.</p>\r\n"
//				+ "\r\n"
//				+ "<img src=\"/images/mancity.jpg\" alt=\"Manchester City ăn mừng bàn thắng\" />\r\n"
//				+ "\r\n"
//				+ "<p>Hai bàn thắng được ghi bởi Erling Haaland và Kevin De Bruyne trong hiệp hai \r\n"
//				+ "sau thế trận kiểm soát hoàn toàn của đội chủ nhà.</p>\r\n"
//				+ "\r\n"
//				+ "<h3>Cuộc đua vô địch</h3>\r\n"
//				+ "<p>Với chiến thắng này, Man City tạo khoảng cách 5 điểm so với đội xếp thứ hai \r\n"
//				+ "khi mùa giải chỉ còn 3 vòng đấu.</p>\r\n"
//				+ "\r\n"
//				+ "<p>HLV Pep Guardiola chia sẻ:</p>\r\n"
//				+ "<blockquote>\r\n"
//				+ "    \"Chúng tôi vẫn phải tập trung tối đa. Mỗi trận đấu đều là một trận chung kết.\"\r\n"
//				+ "</blockquote>\r\n"
//				+ "\r\n"
//				+ "<p>Nếu tiếp tục giữ vững phong độ, Manchester City hoàn toàn có thể bảo vệ \r\n"
//				+ "thành công ngôi vương mùa giải này.</p>\r\n"
//				);
//		n3.setViews(720);
//		n3.setStatus(NewStatus.PUBLISHED.name());
//		n3.setIsFeatured(false);
//		n3.setPriority(5);
//		n3.setUser(admin);
//		n3.setCreatedDate(date1.plusDays(5));
//		Set<CategoriesEntity> s3 = new HashSet<>();
//		s3.add(c1);
//		s3.add(c2);
//		n3.setCategories(s3);
//		Set<TagsEntity> tag3 = new HashSet<TagsEntity>();
//		tag3.add(t5);
//		n3.setTags(tag3);
//		newsRepository.save(n3);
//		
//		NewsEntity n4 = new NewsEntity();
//		n4.setTitle("Barcelona thắng đậm 4-0 tại La Liga");
//		n4.setSlug("barcelona-thang-dam-4-0-la-liga");
//		n4.setSummary("Barcelona tiếp tục phong độ ấn tượng với chiến thắng đậm 4-0.");
//		n4.setContent("<h2>Barcelona áp đảo hoàn toàn</h2>"
//		        + "<p>Đội bóng xứ Catalan thể hiện sức mạnh vượt trội khi ghi 4 bàn thắng "
//		        + "vào lưới đối thủ trong trận đấu tối qua.</p>"
//		        + "<img src=\"/images/barca1.jpg\" alt=\"Barcelona ghi bàn\" />"
//		        + "<p>Chiến thắng giúp Barcelona tiếp tục bám đuổi ngôi đầu bảng.</p>");
//		n4.setViews(980);
//		n4.setStatus(NewStatus.PUBLISHED.name());
//		n4.setIsFeatured(false);
//		n4.setPriority(5);
//		n4.setUser(admin);
//		n4.setCreatedDate(LocalDateTime.now());
//		Set<CategoriesEntity> s4 = new HashSet<>();
//		s4.add(c1);
//		s4.add(c3);
//		n4.setCategories(s4);
//		Set<TagsEntity> tag4 = new HashSet<>();
//		tag4.add(t1);
//		n4.setTags(tag4);
//		newsRepository.save(n4);
//
//
//		NewsEntity n5 = new NewsEntity();
//		n5.setTitle("Real Madrid giành chiến thắng kịch tính phút cuối");
//		n5.setSlug("real-madrid-thang-phut-cuoi");
//		n5.setSummary("Bàn thắng ở phút bù giờ giúp Real Madrid giành trọn 3 điểm.");
//		n5.setContent("<h2>Khoảnh khắc tỏa sáng</h2>"
//		        + "<p>Real Madrid đã có chiến thắng nghẹt thở nhờ bàn thắng "
//		        + "ở những phút cuối cùng của trận đấu.</p>"
//		        + "<img src=\"/images/real1.jpg\" alt=\"Real Madrid celebration\" />");
//		n5.setViews(1430);
//		n5.setStatus(NewStatus.PUBLISHED.name());
//		n5.setIsFeatured(true);
//		n5.setPriority(8);
//		n5.setUser(admin);
//		n5.setCreatedDate(LocalDateTime.now());
//		Set<CategoriesEntity> s5 = new HashSet<>();
//		s5.add(c1);
//		s5.add(c2);
//		n5.setCategories(s5);
//		Set<TagsEntity> tag5 = new HashSet<>();
//		tag5.add(t4);
//		n5.setTags(tag5);
//		newsRepository.save(n5);
//
//
//		NewsEntity n6 = new NewsEntity();
//		n6.setTitle("Premier League bước vào giai đoạn căng thẳng");
//		n6.setSlug("premier-league-giai-doan-cang-thang");
//		n6.setSummary("Cuộc đua vô địch Premier League đang trở nên cực kỳ hấp dẫn.");
//		n6.setContent("<h2>Cuộc đua tam mã</h2>"
//		        + "<p>Ba đội bóng hàng đầu đang cạnh tranh quyết liệt cho chức vô địch.</p>"
//		        + "<img src=\"/images/epl1.jpg\" alt=\"Premier League\" />");
//		n6.setViews(760);
//		n6.setStatus(NewStatus.PUBLISHED.name());
//		n6.setIsFeatured(false);
//		n6.setPriority(6);
//		n6.setUser(admin);
//		n6.setCreatedDate(LocalDateTime.now());
//		Set<CategoriesEntity> s6 = new HashSet<>();
//		s6.add(c1);
//		s6.add(c4);
//		s6.add(c7);
//		n6.setCategories(s6);
//		Set<TagsEntity> tag6 = new HashSet<>();
//		tag6.add(t1);
//		n6.setTags(tag6);
//		newsRepository.save(n6);
//
//
//		NewsEntity n7 = new NewsEntity();
//		n7.setTitle("Mbappe tỏa sáng với cú đúp cho PSG");
//		n7.setSlug("mbappe-cu-dup-psg");
//		n7.setSummary("Mbappe ghi hai bàn thắng giúp PSG tiếp tục dẫn đầu.");
//		n7.setContent("<h2>Ngôi sao người Pháp</h2>"
//		        + "<p>Kylian Mbappe tiếp tục chứng minh giá trị khi lập cú đúp "
//		        + "trong chiến thắng của PSG.</p>"
//		        + "<img src=\"/images/mbappe1.jpg\" alt=\"Mbappe ghi bàn\" />");
//		n7.setViews(1110);
//		n7.setStatus(NewStatus.PUBLISHED.name());
//		n7.setIsFeatured(false);
//		n7.setPriority(7);
//		n7.setUser(admin);
//		n7.setCreatedDate(LocalDateTime.now());
//		Set<CategoriesEntity> s7 = new HashSet<>();
//		s7.add(c5);
//		s7.add(c4);
//		n7.setCategories(s7);
//		Set<TagsEntity> tag7 = new HashSet<>();
//		tag7.add(t4);
//		n7.setTags(tag7);
//		newsRepository.save(n7);
//
//
//		NewsEntity n8 = new NewsEntity();
//		n8.setTitle("Inter Milan tiếp tục dẫn đầu Serie A");
//		n8.setSlug("inter-milan-dan-dau-serie-a");
//		n8.setSummary("Inter Milan giữ vững ngôi đầu bảng với chiến thắng quan trọng.");
//		n8.setContent("<h2>Inter Milan ổn định phong độ</h2>"
//		        + "<p>Chiến thắng mới nhất giúp Inter Milan củng cố vị trí số một "
//		        + "trên bảng xếp hạng Serie A.</p>"
//		        + "<img src=\"/images/inter1.jpg\" alt=\"Inter Milan\" />");
//		n8.setViews(640);
//		n8.setStatus(NewStatus.PUBLISHED.name());
//		n8.setIsFeatured(false);
//		n8.setPriority(4);
//		n8.setUser(admin);
//		n8.setCreatedDate(LocalDateTime.now());
//		Set<CategoriesEntity> s8 = new HashSet<>();
//		s8.add(c3);
//		s8.add(c5);
//		n8.setCategories(s8);
//		Set<TagsEntity> tag8 = new HashSet<>();
//		tag8.add(t1);
//		n8.setTags(tag8);
//		newsRepository.save(n8);
//
//		
//		
//		
//		
//	}
//
//}
