--
-- Βάση δεδομένων: `kc_db`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `exams`
--

CREATE TABLE `exams` (
  `exam_id` int(2) NOT NULL,
  `exam_datetime` datetime NOT NULL,
  `exam_center_id` int(2) NOT NULL,
  `exam_status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `exams`
--

INSERT INTO `exams` (`exam_id`, `exam_datetime`, `exam_center_id`, `exam_status`) VALUES
(1, '2018-03-09 20:00:00', 1, 1),
(2, '2018-03-20 18:00:00', 2, 1);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `exam_centers`
--

CREATE TABLE `exam_centers` (
  `exam_center_id` int(2) NOT NULL,
  `exam_center_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `exam_center_addr` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `exam_centers`
--

INSERT INTO `exam_centers` (`exam_center_id`, `exam_center_name`, `exam_center_addr`, `user_id`) VALUES
(1, 'Ανθούπολης', 'Ανθούπολης 12, Ανθούπολη', 3),
(2, 'Αιγαλέου', 'Αιγαλέου 13, Αιγάλεω', 3),
(3, 'Χίου ΙΙ', 'Χίου 14, Χίος', 3),
(4, 'Χίος', 'Χίου 1, Χίος', NULL);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `exam_matches`
--

CREATE TABLE `exam_matches` (
  `match_id` int(2) NOT NULL,
  `exam_id` int(2) NOT NULL,
  `user_id` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `exam_matches`
--

INSERT INTO `exam_matches` (`match_id`, `exam_id`, `user_id`) VALUES
(1, 1, 2),
(2, 2, 4);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `logs`
--

CREATE TABLE `logs` (
  `log_id` int(3) NOT NULL,
  `match_id` int(2) NOT NULL,
  `question_id` int(2) NOT NULL,
  `question_answer` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer_user` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `questions`
--

CREATE TABLE `questions` (
  `question_id` int(3) NOT NULL,
  `question` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer1` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer2` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer3` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer4` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `question_answer` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `questions`
--

INSERT INTO `questions` (`question_id`, `question`, `question_answer1`, `question_answer2`, `question_answer3`, `question_answer4`, `question_answer`) VALUES
(1, 'Ποιος ήταν ο πρώτος πρωθυπουργός της Ελλάδας;', 'Ιωάννης Καποδίστριας ', 'Ανδρέας Παπανδρέου', 'Κωνσταντίνος Καραμανλής', 'Θεόδωρος Κολοκοτρώνης ', 'Ιωάννης Καποδίστριας '),
(2, 'Πόσο κάνει 6 επί 7 επί 8;', '336', '346', '326', '356', '336'),
(3, 'Ποια είναι η πρωτεύουσα της Ελλάδας;', 'Κοζάνη', 'Θεσσαλονίκη', 'Πάτρα', 'Αθήνα', 'Αθήνα'),
(4, 'Τι δέντρο στολίζουμε τα Χριστούγεννα;', 'Έλατο', 'Πεύκο', 'Ελιά', 'Πορτοκαλιά', 'Έλατο'),
(5, 'Πότε ιδρύθηκε η ΑΕΚ;', '1922', '1928', '1924', '1912', '1924'),
(6, 'Ποιος ανακάλυψε τον ασύρματο τηλέγραφο;', 'Κάρολος Τζέκινς', 'Θωμάς Έντισον', 'Σαμουήλ Μορς', 'Γουλιέλμος Μαρκόνι', 'Γουλιέλμος Μαρκόνι'),
(7, 'Άμεση δημοκρατία συναντούμε:', 'στη σύγχρονη Ελλάδα', 'στις Η.Π.Α.', 'στην αρχαία Ελλάδα', 'στην Κούβα', 'στην αρχαία Ελλάδα'),
(8, 'Που έζησε ο Καβάφης;', 'Σμύρνη', 'Κάιρο', 'Αλεξάνδρεια', 'Βηρυτό', 'Αλεξάνδρεια'),
(9, 'Ποια ήταν η νικήτρια χώρα στη Eurovision το 2005;', 'Τουρκία', 'Ισπανία', 'Αγγλία', 'Ελλάδα', 'Ελλάδα'),
(10, 'Σε ποια ήπειρο βρίσκεται το Κατάρ;', 'Ασία', 'Ευρώπη', 'Αφρική', 'Αυστραλία', 'Ασία'),
(11, 'Ποιος έγραψε τη σειρά Δυο ξένοι;', 'Ρήγας-Αποστόλου', 'Ρώμας-Χατζησοφιά', 'Ρίσβας-Σακκαλή', 'Θωμόπουλος', 'Ρήγας-Αποστόλου'),
(12, 'Πως λέγεται το DNA στα ελληνικά;', 'Ριβονουκλεινικό οξύ', 'Ασπιρίνη', 'Δεσοξυριβονουκλεινικό οξύ', 'Ακετυλοχολίνη', 'Δεσοξυριβονουκλεινικό οξύ'),
(13, 'Ποιος τραγουδιστής λέει το "Φεγγάρι μου χλωμό";', 'Πασχάλης Τερζής', 'Βασίλης Καρράς', 'Νίκος Μακρόπουλος', 'Νίκος Καζαντζίδης', 'Πασχάλης Τερζής'),
(14, 'Η πρωτεύουσα της Γαλλίας είναι:', 'Μπορντώ', 'Παρίσι', 'Λονδίνο', 'Λυών', 'Παρίσι'),
(15, 'Ποιος ίδρυσε το συγκρότημα U2;', 'Λάρρυ Μιούλεν Τζούνιορ', 'Πολ Χιούσον (Μπόνο)', 'Ντέιβιντ Ίβανς', 'Άνταμ Κλέιτον', 'Λάρρυ Μιούλεν Τζούνιορ'),
(16, 'Τι σημαίνει bon appetit;', 'Καλησπέρα', 'Αντίο', 'Καλή όρεξη', 'Τα λέμε', 'Καλή όρεξη'),
(17, 'Ποιο είναι το ταχύτερο πουλί στον κόσμο;', 'Αετός', 'Γλάρος', 'Πελαργός', 'Πετροχελίδονο', 'Πετροχελίδονο'),
(18, '«Νέο Άμστερνταμ» ήταν το πρώτο όνομα της πόλης:', 'Σικάγο', 'Άμστερνταμ', 'Νέα Υόρκη', 'Λος Άντζελες', 'Νέα Υόρκη'),
(19, 'Ποιος ήταν ο διάδοχος του Χίτλερ;', 'Γκέμπελς', 'Ντένιτς', 'Γκας', 'Ουίλιαμς', 'Ντένιτς'),
(20, 'Με πόσα βήματα χορεύεται το τσάμικο;', '12', '9', '6', '15', '12'),
(21, 'Ποιος έγραψε το  μυθιστόρημα “Ερωτόκριτος”;', 'Β.Κορνάρος', 'Γ.Χορτάτσης', 'Γ.Φραντζής', 'Ν.Καββαδίας', 'Β.Κορνάρος'),
(22, 'Πότε κατασκευάστηκε το πρώτο λέιζερ;', '1996', '1956', '1960', '1903', '1960'),
(23, 'Πόσες χορδές έχει ένα βιολί;', '8', '3', '4', '5', '4'),
(24, 'Από που καταγόνταν ο Ελ. Βενιζέλος;', 'Θεσσαλονίκη', 'Ψαρά', 'Χίο', 'Κρήτη', 'Κρήτη'),
(25, 'Το θειϊκό οξύ λέγεται και;', 'Βιτριόλι', 'Κεζάπι', 'Ακουαφόρτε', 'Βασιλικόν ύδωρ', 'Βιτριόλι'),
(26, 'Ποιος πήρε μέρος στη μάχη του Μαραθώνα το 490 π.Χ.;', 'Θεμιστοκλής', 'Μιλτιάδης', 'Παυσανίας', 'Λεωνίδας', 'Μιλτιάδης'),
(27, 'Ποια είναι η μεγαλύτερη σε έκταση λίμνη της Γής;', 'Κασπία', 'Βικτορία', 'Μεγάλη Πρέσπα', 'Ταγκανίκα', 'Κασπία'),
(28, 'Ποιο είναι το μεγαλύτερο λιμάνι διακίνησης φορτίου στον κόσμο;', 'Ρότερνταμ', 'Πειραιάς', 'Σιγκαπούρη', 'Νέα Υόρκη', 'Ρότερνταμ'),
(29, 'Ποιος είναι ο μεγαλύτερος και βαθύτερος ωκεανός;', 'Ατλαντικός', 'Ειρηνικός', 'Ινδικός', 'Κανένας από αυτούς', 'Ειρηνικός'),
(30, 'Ως σημαντικότερος ιστορικός της αρχαιότητας θεωρείται ο:', 'Παυσανίας', 'Ερατοσθένης', 'Ηρόδοτος', 'Εμπεδοκλής', 'Ηρόδοτος'),
(31, 'Ποιο κράτος έχει σήμερα βασιλευομένη δημοκρατία;', 'Ισπανία', 'Ιταλία', 'Γαλλία', 'Γερμανία', 'Ισπανία'),
(32, 'Η έδρα της Διεθνούς Ολυμπιακής Επιτροπής βρίσκεται:', 'στη Βέρνη', 'στη Γενεύη', 'στο Παρίσι', 'στη Λωζάννη', 'στη Λωζάννη'),
(33, '           Η πιο αδύναμη αίσθηση της καφέ αρκούδας είναι;', 'Όραση', 'Ακοή', 'Όσφρηση', 'Γεύση', 'Όραση'),
(34, 'Ποια είναι η μακρύτερη οροσειρά της Γής;', 'Άλπεις', 'Άνδεις', 'Πυρηναία Όρη', 'Άτλας', 'Άνδεις'),
(35, '«Ζορό» στα ισπανικά σημαίνει:', 'Αρουραίος', ' Αλεπού', 'Νυφίτσα', 'Ποντίκι', ' Αλεπού'),
(36, 'Πότε ιδρύθηκε η Κωνσταντινούπολη;', 'το 350 π.Χ.', 'το 320 π.Χ.', 'το 330 π.Χ.', 'το 340 π.Χ.', 'το 330 π.Χ.'),
(37, 'Από ποια χώρα προέρχεται το τυρί χαλούμι;', 'Αλβανία', 'Βουλγαρία', 'Τουρκία', 'Κύπρο', 'Κύπρο'),
(38, 'Πότε έληξε ο δεύτερος Παγκόσμιος πόλεμος;', '1941', '1943', '1945', '1967', '1945'),
(39, 'Πότε γεννήθηκε ο Νεύτωνας;', '1643', '1651', '1638', '1705', '1643'),
(40, 'Ποια είναι η πρωτεύουσα της Τσεχίας;', 'Μπρνό', 'Πίλζεν', 'Πράγα', 'Κάρλοβυ Βάρυ', 'Πράγα'),
(41, 'Πόσους κατοίκους έχει η Ισλανδία;', '334.252', '1.650.756', '3.559.120', '180.085', '334.252'),
(42, 'Ποια ήταν η πρώτη πρωτεύουσα της Ελλάδας;', 'Αθήνα', 'Πάτρα', 'Ναύπλιο', 'Θεσσαλονίκη', 'Ναύπλιο'),
(43, 'Ο μπακλαβάς είναι παραδοσιακό γλυκό της;', 'Τουρκίας', 'Ελλάδας', 'Ισραήλ', 'Κύπρου', 'Τουρκίας'),
(44, 'Σε πόσους βαθμούς κελσίου το νερό γίνεται πάγος;', '-100', '0', '100', '-50', '0'),
(45, 'Ποιο είναι το νόμισμα της Πολωνίας;', 'Ευρώ', 'Κορώνα', 'Δραχμή', 'Σλότι', 'Σλότι'),
(46, 'Ποια χώρα φημίζεται για τα ρολόγια της;', 'Αυστρία', 'Ουγγαρία', 'Ελβετία', 'Κροατία', 'Ελβετία'),
(47, 'Ο Αλέξης Τσίπρας εκλέχτηκε για πρώτη φορα πρωθυπουργός το:', '2004', '2015', '2009', '2011', '2015'),
(48, 'Από που προέρχονται τα σκυλιά της Δαλματίας;', 'Γαλλία', 'Κροατία', 'Ρουμανία', 'Ελβετία', 'Κροατία'),
(49, 'Ποια δύο βασικά χρώματα μαζί κάνουν το πράσινο;', 'Μπλέ και κίτρινο', 'Κόκκινο και κίτρινο', 'Μπλε και κόκκινο', 'Άσπρο και μαύρο', 'Μπλε και κίτρινο'),
(50, 'Ποιος απόστολος του Χριστού έφτασε μέχρι τις Ινδίες;', 'Πέτρος', 'Λουκάς', 'Παύλος', 'Θωμάς', 'Θωμάς');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `results`
--

CREATE TABLE `results` (
  `result_id` int(3) NOT NULL,
  `match_id` int(2) NOT NULL,
  `result` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `roles`
--

CREATE TABLE `roles` (
  `role_id` int(1) NOT NULL,
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `roles`
--

INSERT INTO `roles` (`role_id`, `role_name`) VALUES
(1, 'Διαχειριστής'),
(2, 'Υπεύθυνος εξεταστικού κέντρου'),
(3, 'Εξεταζόμενος');

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `users`
--

CREATE TABLE `users` (
  `user_id` int(2) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role_id` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Άδειασμα δεδομένων του πίνακα `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `full_name`, `role_id`) VALUES
(1, 'admin', 'admin', 'Ιωάννης Κυρίτσης', 1),
(2, 'dim', '1234', 'Δημήτρης Ράκας', 3),
(3, 'theo', '1234', 'Αξιώτης Θεόφιλος', 2),
(4, 'ioulios', '1234', 'Ιούλιος Τσίκο', 3),
(5, 'user1', 'user1', 'user1', 3),
(6, 'user2', 'user2', 'user2', 3),
(7, 'user3', 'user3', 'user3', 3),
(8, 'user4', 'user4', 'user4', 3),
(9, 'user5', 'user5', 'user5', 3);

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `exams`
--
ALTER TABLE `exams`
  ADD PRIMARY KEY (`exam_id`),
  ADD UNIQUE KEY `exam_datetime` (`exam_datetime`,`exam_center_id`),
  ADD KEY `exam_center_id` (`exam_center_id`);

--
-- Ευρετήρια για πίνακα `exam_centers`
--
ALTER TABLE `exam_centers`
  ADD PRIMARY KEY (`exam_center_id`),
  ADD UNIQUE KEY `exam_center_name` (`exam_center_name`),
  ADD KEY `user_id` (`user_id`);

--
-- Ευρετήρια για πίνακα `exam_matches`
--
ALTER TABLE `exam_matches`
  ADD PRIMARY KEY (`match_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `exam_id` (`exam_id`);

--
-- Ευρετήρια για πίνακα `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `match_id` (`match_id`),
  ADD KEY `question_id` (`question_id`);

--
-- Ευρετήρια για πίνακα `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`question_id`);

--
-- Ευρετήρια για πίνακα `results`
--
ALTER TABLE `results`
  ADD PRIMARY KEY (`result_id`),
  ADD KEY `match_id` (`match_id`);

--
-- Ευρετήρια για πίνακα `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Ευρετήρια για πίνακα `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `exams`
--
ALTER TABLE `exams`
  MODIFY `exam_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT για πίνακα `exam_centers`
--
ALTER TABLE `exam_centers`
  MODIFY `exam_center_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT για πίνακα `exam_matches`
--
ALTER TABLE `exam_matches`
  MODIFY `match_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT για πίνακα `logs`
--
ALTER TABLE `logs`
  MODIFY `log_id` int(3) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT για πίνακα `questions`
--
ALTER TABLE `questions`
  MODIFY `question_id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT για πίνακα `results`
--
ALTER TABLE `results`
  MODIFY `result_id` int(3) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT για πίνακα `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT για πίνακα `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `exams`
--
ALTER TABLE `exams`
  ADD CONSTRAINT `exams_ibfk_1` FOREIGN KEY (`exam_center_id`) REFERENCES `exam_centers` (`exam_center_id`);

--
-- Περιορισμοί για πίνακα `exam_centers`
--
ALTER TABLE `exam_centers`
  ADD CONSTRAINT `exam_centers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Περιορισμοί για πίνακα `exam_matches`
--
ALTER TABLE `exam_matches`
  ADD CONSTRAINT `exam_matches_ibfk_1` FOREIGN KEY (`exam_id`) REFERENCES `exams` (`exam_id`),
  ADD CONSTRAINT `exam_matches_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Περιορισμοί για πίνακα `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`match_id`) REFERENCES `exam_matches` (`match_id`),
  ADD CONSTRAINT `logs_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`);

--
-- Περιορισμοί για πίνακα `results`
--
ALTER TABLE `results`
  ADD CONSTRAINT `results_ibfk_1` FOREIGN KEY (`match_id`) REFERENCES `exam_matches` (`match_id`);

--
-- Περιορισμοί για πίνακα `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
