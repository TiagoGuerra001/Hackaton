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
        brain.setInfo("Stuff about the brain.");

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
        cortex.setColor("#00FF00");
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
        FrontLobe.setColor("#FF0000");
        FrontLobe.setMainPart(brain);

        BodyPart Cerebellum = new BodyPart();
        Cerebellum.setName("Cerebellum");
        Cerebellum.setInfo("Stuff about the Cerebellum.");
        FrontLobe.setColor("#0000FF");
        Cerebellum.setMainPart(brain);

        // Create a Question
        List<String> questionsStrings = new ArrayList<>();
        questionsStrings.add("wrong");
        questionsStrings.add("wrong");
        questionsStrings.add("wrong");
        Question question1 = new Question();
        question1.setText("Pergunta numero 1:");
        question1.setDifficulty(1);
        question1.setWrongAnswers(questionsStrings);
        question1.setCorrectAnswer("right1");
        question1.setBodyPart(cortex);

        Question question2 = new Question();
        question2.setText("Pergunta numero 2:");
        question2.setDifficulty(1);
        question2.setWrongAnswers(questionsStrings);
        question2.setCorrectAnswer("right2");
        question2.setBodyPart(cortex);

        Question question3 = new Question();
        question3.setText("Pergunta numero 3:");
        question3.setDifficulty(1);
        question3.setWrongAnswers(questionsStrings);
        question3.setCorrectAnswer("right3");
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

        // Create a Question
        List<String> questionsStrings2 = new ArrayList<>();
        questionsStrings2.add("wrong");
        questionsStrings2.add("wrong");
        questionsStrings2.add("wrong");
        Question question4 = new Question();
        question4.setText("Pergunta numero 4:");
        question4.setDifficulty(1);
        question4.setWrongAnswers(questionsStrings2);
        question4.setCorrectAnswer("right4");
        question4.setBodyPart(atrium);

        Question question5 = new Question();
        question5.setText("Pergunta numero 5:");
        question5.setDifficulty(1);
        question5.setWrongAnswers(questionsStrings2);
        question5.setCorrectAnswer("right5");
        question5.setBodyPart(atrium);

        Question question6 = new Question();
        question6.setText("Pergunta numero 6:");
        question6.setDifficulty(1);
        question6.setWrongAnswers(questionsStrings2);
        question6.setCorrectAnswer("right6");
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