package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.MainPartRepository;
import com.example.demo.repository.BodyPartRepository;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.repository.UserDAORepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.entity.MainPart;
import com.example.demo.entity.BodyPart;
import com.example.demo.entity.Question;
import com.example.demo.entity.Item;
import com.example.demo.entity.UserDAO;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MainBodyPartService {

    @Autowired
    private MainPartRepository mainBodyPartRepository;
    @Autowired
    private BodyPartRepository bodyPartRepository;
    @Autowired
    private QuestionsRepository questionRepository;
    @Autowired
    private UserDAORepository userDAORepository;
    @Autowired
    private ItemRepository itemRepository;
    // get passwordencoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void populateDatabase() {
        // create user
        UserDAO user = new UserDAO();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setEmail("1@1");
        user.setCoins(70);
        userDAORepository.save(user);
        // Create a MainPart
        MainPart brain = new MainPart();
        brain.setName("Brain");
        brain.setInfo("The brain consists of several main parts, including the cerebrum, cerebellum," + 
        " and brainstem. The cerebrum is responsible for higher functions like thinking and voluntary movement," + 
        " while the cerebellum coordinates movement and balance. The brainstem controls basic bodily functions "+
        "like breathing and heartbeat.");

        // Create a BodyPart
        BodyPart cortex = new BodyPart();
        cortex.setName("Cortex");
        cortex.setInfo("The brain cortex, or cerebral cortex, is the outer layer of neural tissue " +
        "of the cerebrum of the brain in humans and other mammals. It is crucial " +
        "for memory, attention, perception, cognition, awareness, thought, language, " +
        "and consciousness. The cerebral cortex is divided into four main lobes: the " +
        "frontal, parietal, temporal, and occipital lobes, each responsible for different " +
        "functions. For instance, the frontal lobe is associated with decision making " +
        "and problem-solving, the parietal lobe with sensation and perception, the temporal " +
        "lobe with memory and emotion, and the occipital lobe with vision.\n" +
        "\n" +
        "The cortex is typically 2 to 4 mm thick and contains about 16 billion neurons. " +
        "The majority of the cerebral cortex is the neocortex, which has six neuronal layers, " +
        "while the allocortex has three or four layers and includes regions like the hippocampus " +
        "and olfactory areas. The cerebral cortex is divided into left and right hemispheres, " +
        "connected by the corpus callosum, with each hemisphere controlling movement and sensation " +
        "in the opposite side of the body.\n" +
        "\n" +
        "The six layers of the neocortex each have different types of neurons with varied functions, " +
        "ranging from input reception to sending output signals to other brain areas. The cortex " +
        "exhibits plasticity, meaning it can reorganize itself by forming new neural connections " +
        "throughout life, particularly after injury. The cortex is also divided into regions called " +
        "Brodmann areas, based on their cytoarchitectural characteristics, which are often used to " +
        "classify cortical functions.\n" +
        "\n" +
        "Several diseases affect the brain cortex, each leading to specific impairments:\n" +
        "\n" +
        "- Alzheimer's Disease is a progressive neurological disorder that causes the brain to shrink " +
        "and neurons to die, particularly affecting the cortex. This results in severe memory loss, " +
        "confusion, mood changes, and difficulty with language.\n" +
        "\n" +
        "- Epilepsy is marked by sudden recurrent episodes of sensory disturbance, loss of consciousness, " +
        "or convulsions, associated with abnormal electrical activity in the cortex.\n" +
        "\n" +
        "- Stroke occurs when the blood supply to the brain is interrupted or reduced, which can prevent " +
        "brain tissue from getting oxygen and nutrients. Strokes often affect the cortex, leading to impairments " +
        "based on the affected areas.\n" +
        "\n" +
        "- Parkinson's Disease primarily affects motor function due to basal ganglia disorder, but it also impacts " +
        "the cortical areas, leading to cognitive and behavioral issues.\n" +
        "\n" +
        "- Multiple Sclerosis (MS) affects the brain and spinal cord, causing communication problems between the brain " +
        "and the rest of the body. In the cortex, MS can lead to lesions that affect cognitive functions.\n" +
        "\n" +
        "- Autism Spectrum Disorder (ASD) is a developmental disorder that affects communication and behavior. Some studies " +
        "suggest that ASD involves irregularities in cortical layers and connections.\n" +
        "\n" +
        "- Traumatic Brain Injury (TBI) affects the cortex and can lead to a range of functional impairments, depending on " +
        "which area of the cortex is affected.\n" +
        "\n" +
        "- Schizophrenia affects a person's ability to think, feel, and behave clearly. Abnormalities in cortical structure and " +
        "function, particularly in the frontal and temporal lobes, are linked to this condition.\n" +
        "\n" +
        "Understanding the brain cortex and its functions helps in diagnosing, treating, and managing various neurological and " +
        "psychological conditions.");
        cortex.setColor("#E74C3C");
        cortex.setMainPart(brain);

        BodyPart FrontLobe = new BodyPart();
        FrontLobe.setName("Front Lobe");
        FrontLobe.setInfo("The frontal lobe is one of the four main lobes of the cerebral cortex in the brain of mammals, " +
        "and it plays a pivotal role in high-level cognitive functions. It is located at the front of each " +
        "hemisphere and is the largest of the brain's lobes. It is responsible for a wide array of complex " +
        "behaviors and functions, including decision-making, problem-solving, conscious thought, and voluntary " +
        "movement.\n" +
        "\n" +
        "This lobe is essential for controlling attention, behavior, and emotions, facilitating the expression " +
        "of personality, and regulating aspects of mood. The frontal lobe is also where our ability to plan and " +
        "organize activities originates, allowing for abstract thinking and creative problem-solving. It houses " +
        "the motor cortex, which manages the execution of voluntary muscle movements, and the prefrontal cortex, " +
        "which is critical for focus, hypothesis formation, and the assessment of consequences.\n" +
        "\n" +
        "Moreover, the frontal lobe plays a crucial role in the management of emotional expressions, impulse control, " +
        "and social behavior. This area of the brain enables individuals to navigate complex social interactions, " +
        "understand and manage personal reactions and emotions, and perform intricate mental processes necessary for " +
        "goal-directed behavior.\n" +
        "\n" +
        "Key Disorders Affecting the Frontal Lobe:\n" +
        "- Alzheimer's Disease: This progressive neurological disorder significantly impacts the frontal lobe, " +
        "particularly as the disease advances. It affects cognitive functions such as memory, planning, and organizational " +
        "skills and leads to changes in personality and social behavior.\n" +
        "- Stroke: A stroke affecting the frontal lobe can cause various symptoms, depending on which part of the lobe is " +
        "impacted. Common consequences include paralysis of parts of the body, problems with speech, loss of fine motor " +
        "skills, and challenges in strategic thinking and problem-solving.\n" +
        "- Traumatic Brain Injury (TBI): Injuries to the frontal lobe can result in a variety of symptoms due to its role in " +
        "personality, cognition, and motor function. Victims often experience changes in their ability to concentrate, shifts " +
        "in personality, impaired memory, difficulty in problem-solving, and erratic or socially inappropriate behavior.\n" +
        "- Frontotemporal Dementia: This form of dementia primarily affects the frontal and temporal lobes of the brain, leading " +
        "to significant alterations in personality and behavior early in the disease, along with language and movement difficulties " +
        "as the condition progresses.\n" +
        "- Parkinson's Disease: While primarily affecting motor control, Parkinson’s disease also impacts the frontal lobe, especially " +
        "in later stages. Symptoms can include changes in cognitive abilities, difficulties with planning and executing tasks, and altered " +
        "social behavior.\n" +
        "\n" +
        "The frontal lobe’s integral role in integrating sensory information and coordinating complex cognitive functions makes it crucial " +
        "for adapting to new situations, modifying behavior in response to new information, and implementing complex plans and strategies."
);
        FrontLobe.setColor("#3498DB");
        FrontLobe.setMainPart(brain);

        BodyPart Cerebellum = new BodyPart();
        Cerebellum.setName("Cerebellum");
        Cerebellum.setInfo("Stuff about the Cerebellum.");
        Cerebellum.setColor("#2ECC71");
        Cerebellum.setMainPart(brain);

        // Create a Question
        List<String> questionsStrings = new ArrayList<>();
        questionsStrings.add("Frontal lobe: balance; Parietal lobe: taste; Temporal lobe: smell; Occipital lobe: touch");
        questionsStrings.add("Frontal lobe: sleep; Parietal lobe: immune response; Temporal lobe: digestion; Occipital lobe: blood pressure");
        questionsStrings.add("Frontal lobe: color perception; Parietal lobe: temperature; Temporal lobe: pain; Occipital lobe: hunger");
        Question question1 = new Question();
        question1.setText("Describe the four main lobes of the cerebral cortex and their primary functions.");
        question1.setDifficulty(1);
        question1.setWrongAnswers(questionsStrings);
        question1.setCorrectAnswer("Frontal lobe: decision-making; Parietal lobe: sensation; Temporal lobe: hearing; Occipital lobe: vision");
        question1.setBodyPart(cortex);

        Question question2 = new Question();
        List<String> questionsStrings2 = new ArrayList<>();
       
        questionsStrings2.add("Its single-layered structure simplifies neural processing, enhancing reflex actions.");
        questionsStrings2.add("Its two-layered architecture helps in basic survival instincts like feeding and fleeing.");
        questionsStrings2.add("Its five-layered structure supports emotional responses to environmental stimuli.");
        question2.setText("How does the structure of the neocortex contribute to its functions in higher cognitive processes?");
        question2.setDifficulty(1);
        question2.setWrongAnswers(questionsStrings2);
        question2.setCorrectAnswer("Its six-layered structure facilitates complex functions like sensory perception, spatial reasoning, and conscious thought.");
        question2.setBodyPart(cortex);

        Question question3 = new Question();
        List<String> questionsStrings3 = new ArrayList<>();
        questionsStrings3.add("Osteoporosis, arthritis, and diabetes: impact bone and glucose metabolism without directly affecting the cortex.");
        questionsStrings3.add("Myopia, hyperopia, and astigmatism: affect vision by altering the lens but not the cortical processing of visual information.");
        questionsStrings3.add("Tachycardia, bradycardia, and arrhythmia: involve heart rate and rhythm, not directly related to cortical functions.");
        question3.setText("What are some common neurological disorders that affect the cortex, and how do they impact human functions?");
        question3.setDifficulty(1);
        question3.setWrongAnswers(questionsStrings3);
        question3.setCorrectAnswer("Epilepsy, multiple sclerosis, and schizophrenia: alter cortical functions, leading to sensory disturbances, cognitive impairments, and behavioral changes.");
        question3.setBodyPart(cortex);

        // save mainpart
        List<BodyPart> bodyParts = new ArrayList<>();
        bodyParts.add(cortex);
        bodyParts.add(FrontLobe);
        bodyParts.add(Cerebellum);
        brain.setBodyParts(bodyParts);
        mainBodyPartRepository.save(brain);

        // save bodypart
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        cortex.setQuestions(questions);
        bodyPartRepository.save(cortex);
        bodyPartRepository.save(FrontLobe);
        bodyPartRepository.save(Cerebellum);

        // save questions
        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

        // Create another MainPart Arm
        MainPart heart = new MainPart();
        heart.setName("Heart");
        heart.setInfo("Stuff about the heart.");

        // Create a BodyPart
        BodyPart atrium = new BodyPart();
        atrium.setName("Atrium");
        atrium.setInfo("Stuff about the atrium.");
        atrium.setColor("#00FF00");
        atrium.setMainPart(heart);

        BodyPart ventricle = new BodyPart();
        ventricle.setName("Ventricle");
        ventricle.setInfo("Stuff about the ventricle.");
        ventricle.setColor("#FF0000");

        BodyPart valve = new BodyPart();
        valve.setName("Valve");
        valve.setInfo("Stuff about the valve.");
        valve.setColor("#0000FF");

        // Create a Question Atrium
        List<String> questionsStrings4 = new ArrayList<>();
        questionsStrings4.add("The atria are primarily responsible for pumping blood out of the heart to the lungs and the rest of the body.");
        questionsStrings4.add("The atria regulate the heart's electrical impulses and ensure the timing of the heartbeat.");
        questionsStrings4.add("The atria produce hormones that control blood pressure.");
        Question question4 = new Question();
        question4.setText("What is the main function of the atria in the human heart?");
        question4.setDifficulty(1);
        question4.setWrongAnswers(questionsStrings4);
        question4.setCorrectAnswer("The atria receive blood returning to the heart from the body and lungs and then pump it to the ventricles.");
        question4.setBodyPart(atrium);

        Question question5 = new Question();
        List<String> questionsStrings5 = new ArrayList<>();
        questionsStrings5.add("The right atrium receives oxygen-rich blood from the lungs and pumps it to the left ventricle.");
        questionsStrings5.add("The right atrium receives oxygen-rich blood from the body and pumps it directly to the lungs.");
        questionsStrings5.add("The right atrium generates the electrical signals that control the heartbeat.");
        question5.setText("Which of the following best describes the role of the right atrium in heart function?");
        question5.setDifficulty(1);
        question5.setWrongAnswers(questionsStrings5);
        question5.setCorrectAnswer("The right atrium receives oxygen-poor blood from the body and pumps it to the right ventricle.");
        question5.setBodyPart(atrium);

        Question question6 = new Question();
        List<String> questionsStrings6 = new ArrayList<>();
        questionsStrings6.add("Myocardial infarction (heart attack)");
        questionsStrings6.add("Atherosclerosis (artery hardening)");
        questionsStrings6.add("Hypertension (high blood pressure)?");
        question6.setText("What common health condition affects the atria and can lead to complications such as stroke?:");
        question6.setDifficulty(1);
        question6.setWrongAnswers(questionsStrings6);
        question6.setCorrectAnswer("Atrial fibrillation (Afib)");
        question6.setBodyPart(atrium);



        // save mainpart
        List<BodyPart> bodyParts2 = new ArrayList<>();
        bodyParts2.add(atrium);
        bodyParts2.add(ventricle);
        bodyParts2.add(valve);
        heart.setBodyParts(bodyParts2);
        mainBodyPartRepository.save(heart);

        // save bodypart
        List<Question> questions2 = new ArrayList<>();
        questions2.add(question4);
        questions2.add(question5);
        questions2.add(question6);
        atrium.setQuestions(questions2);
        bodyPartRepository.save(atrium);
        bodyPartRepository.save(ventricle);
        bodyPartRepository.save(valve);

        // save questions
        questionRepository.save(question4);
        questionRepository.save(question5);
        questionRepository.save(question6);

        //Create Item
        Item liver = new Item();
        liver.setName("Liver");
        liver.setInfo("Stuff about the liver.");
        liver.setPrice(10);
        liver.setImageID("liver.png");

        Item lungs = new Item();
        lungs.setName("Lungs");
        lungs.setInfo("Stuff about the lungs.");
        lungs.setPrice(20);
        lungs.setImageID("lungs.png");

        Item stomach = new Item();
        stomach.setName("Stomach");
        stomach.setInfo("Stuff about the stomach.");
        stomach.setPrice(30);
        stomach.setImageID("stomach.png");

        Item muscleAnat = new Item();
        muscleAnat.setName("Muscle Anatomy");
        muscleAnat.setInfo("Stuff about muscle anatomy.");
        muscleAnat.setPrice(40);
        muscleAnat.setImageID("musclesAnatomy.png");

        Item skeletonAnat = new Item();
        skeletonAnat.setName("Skeleton Anatomy");
        skeletonAnat.setInfo("Stuff about skeleton anatomy.");
        skeletonAnat.setPrice(50);
        skeletonAnat.setImageID("skeletonAnatomy.png");

        Item circulationAnat = new Item();
        circulationAnat.setName("Circulation Anatomy");
        circulationAnat.setInfo("Stuff about circulation anatomy.");
        circulationAnat.setPrice(60);
        circulationAnat.setImageID("circulationAnatomy.png");

        itemRepository.save(liver);
        itemRepository.save(lungs);
        itemRepository.save(stomach);
        itemRepository.save(muscleAnat);
        itemRepository.save(skeletonAnat);
        itemRepository.save(circulationAnat);
        


    }
}