package br.edu.fatec.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.edu.fatec.main.enumeration.Theme;
import br.edu.fatec.main.model.ArticleModel;
import br.edu.fatec.main.persistency.ArticleRepository;

@Component
//@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class InitializeDB implements CommandLineRunner {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		List<ArticleModel> articles = articleRepository.findAllByTheme(Theme.TIPOS_TORRA);
		if(articles.isEmpty()) {
			ArticleModel article;
			article = new ArticleModel();
			article.setTitle("Torra Clara");
			article.setTheme(Theme.TIPOS_TORRA);
			article.setArticle("Esse tipo de torra irá acentuar a acidez e o aroma com suavidade do sabor."+
					" O sabor não será amargo e terá pouco corpo. A torra clara preserva os óleos aromáticos,"+
					" que poderão ser vistos pois os grãos estarão secos.\n" + 
					"Ideal para máquinas de café expresso.");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Torra Média");
			article.setTheme(Theme.TIPOS_TORRA);
			article.setArticle("O ponto de equilíbrio entre a acidez, o aroma e o amargor."+
					" Com o corpo mais acentuado do que a torra clara.\n" + 
					"Ideal para coador de pano ou filtro de papel.");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Torra Escura");
			article.setTheme(Theme.TIPOS_TORRA);
			article.setArticle("Esse tipo de torra irá trazer um café menos ácido," +
					" mais amargo e mais encorpado. Pode ser confundido com um café mais " +
					"forte, apesar de ter a mesma quantidade de cafeína que os demais.");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Filtro de Papel ou Coador de Pano");
			article.setTheme(Theme.METODO_PREPARO);
			article.setArticle("o	Utilize cafés com moagem média ou moagem fina.\n" + 
					"o	Coloque o pó no filtro. Não há a necessidade de compactar o pó, apenas espalhe-o igualmente pelo filtro.\n" + 
					"o	Com a água aquecida à 90ºC (logo antes da fervura, após as primeiras bolhas formarem)," +
					" despeje a água sobre todo o pó, começando as beiradas e sem mexer com a colher");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Cafeteira Italiana ou Moka");
			article.setTheme(Theme.METODO_PREPARO);
			article.setArticle("o	Use a moagem grossa.\n" + 
					"o	Coloque a água até a marca indicada na cafeteira.\n" + 
					"o	Coloque a quantidade de pó necessária e leve ao fogo. Quando a água começar a subir," +
					" tire a cafeteira do fogo.\n" + 
					"o	Antes de servir, mexa o café com uma colher na cafeteira para uniformizar a densidade.");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Prensa Francesa");
			article.setTheme(Theme.METODO_PREPARO);
			article.setArticle("o	A moagem deve ser grossa.\n" + 
					"o	Escalque a cafeteira para manter o café quente.\n" + 
					"o	Depois adicione o pó (A proporção mais utilizada é de 8 gramas de pó para 100 ml de água.)\n" + 
					"o	Esquente a água entre 80ºC e 90ºC e coloque na cafeteira até a marca da cafeteira.\n" + 
					"o	Mexa com uma colher para uniformizar o pó em infusão com a água e encaixe a parte de cima da cafeteira com o êmbolo levantado (não abaixe o êmbolo ainda).\n" + 
					"o	Deixe que a infusão do café aconteça por quatro minutos.\n" + 
					"o	Abaixe o êmbolo com delicadeza, não muito rápido pois essa parte fará a separação do pó e do café pronto.\n" + 
					"o	Sirva.");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Aeropress");
			article.setTheme(Theme.METODO_PREPARO);
			article.setArticle("o	Existem dois tipos de filtro: papel e metálico. O filtro de papel retém alguns óleos (aromas), ele é descartável, possui preço acessível, e produz uma bebida limpa e menos amarga. O filtro metálico não é descartável e a bebida é menos limpa, não segura tantas partículas portanto é mais amarga, é lavável e durável.\n" + 
					"o	Escalde o filtro.\n" + 
					"o	Existem duas maneiras para o preparo do café nesse método: o TRADICIONAL e o INVERTIDO\n" + 
					"o	TRADICIONAL – Coloque o pó de café dentro da AeroPress, zere a balança, adicione água até a marca 1, aguarde 15 segundos, depois coloque mais água até marcar 220g de água (até a marca número 4). Tampe com o pistão e aguarde 1 minuto. Depois  pressione.\n" + 
					"o	INVERTIDA – Encaixe o êmbolo na camisa do pistão, vire-o de cabeça para baixo, de modo que o encaixe da peneira fique virado para cima. Coloque o pó de café dentro da AeroPress, zere a balança, adicione água até a marca 4, aguarde 15 segundos, depois coloque mais água até marcar 220g de água (até a marca número 1). Tampe com o filtro e aguarde 1 minuto. Depois é só virar o lado da peneira para baixo (dentro da caneca) e pressionar.\n");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Hario V60");
			article.setTheme(Theme.METODO_PREPARO);
			article.setArticle("o	Hario V60 é um método japonês que apresenta como diferencial o porta filtro com linhas espirais na parte interna, que proporcionam a expansão do pó de café no momento em que a bebida é coada. E uma abertura grande na base do coador permitindo que a velocidade seja controlada.\n" + 
					"o	O filtro de papel utilizado é desenhado em formato de cone permitindo que a filtragem seja feita igualmente, para deixar que a água flua sem maiores problemas.\n" + 
					"o	A água precisa estar na temperatura média de 92ºC.\n" + 
					"o	O método de preparo é parecido com o de Filtro de Papel.\n");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Café Turco");
			article.setTheme(Theme.METODO_PREPARO);
			article.setArticle("o	Ficou conhecido mundialmente devido à borra densa que deixa no fundo da xícara e é usada para prever o futuro para os que crêem.\n" + 
					"o	O correto é utilizar um recipiente típico da região feito de cobre e bronze, com um punho longo, chamado de Ibrik.\n" + 
					"o	A moagem é a mais fina que tem.\n" + 
					"o	Coloque 50ml de água no Ibrik para cada xícara de café, adicionar açúcar a gosto e misturar bem. Pode ser usado especiarias como: cardamomo, canela e anis estrelado. Depois, deixar o recipiente no fogo até levantar fervura.\n" + 
					"o	Após levantar fervura acrescente 1 colher de chá de café para cada xícara. O café então deve ser fervido novamente, duas vezes, retirando o Ibrik do fogo entre a primeira e a segunda ebulição. Após, misture a bebida para eliminar a espuma.\n" + 
					"o	O café turco deve ser servido sem filtrar.\n" + 
					"o	Assim que o café turco for servido, é de bom feitio esperar um minuto para que o pó assente no fundo da xícara, após esse tempo, deguste. Normalmente não há a necessidade de acrescentar açucar, pois essa etapa já foi concluída no começo do prepato.\n");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Espresso");
			article.setTheme(Theme.METODO_PREPARO);
			article.setArticle("o	A moagem do pó é de média a grossa. Se for muito grossa o café passará num tempo curto, ficará fraco e não formará a crema. Se for muito fina, o café irá passar num tempo maior, terá o sabor mais amargo e crema branca.\n" + 
					"o	O pó colocado na porta do filtro deve ser compactado/prensado corretamente. Se pouco compactado, o café não sairá muito cremoso. Se muito compacto/prensado, o café demora a sair e resultado será um café muito concentrado.\n" + 
					"o	O tempo de preparo do expresso é de 20 a 30 segundos.\n" + 
					"o	Água em torno de 90ºC, podemos identificar essa temperatura via termômetro ou quando começar as primeiras bolhas na água antes de ferver.\n" + 
					"o	Crema marrom claro de 3 a 4mm de espessura.\n" + 
					"o	Terá um aroma forte e característico, se preparado corretamente.\n");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Libérica");
			article.setTheme(Theme.TIPOS_GRAOS);
			article.setArticle("Essa espécia de café foi descoberta na Libéria, no Oeste da África. " +
					"Ela cresce mais de 9 metros e dão cerejas maiores do que as plantas Arábicas. " +
					"A planta foi trazida para a Indonésia no final do século 19, " +
					"para substituir as árvores de Arábica queimadas pela doença da ferrugem do café. " +
					"É similar aos grãos Robusta, em quesito sabor, e ainda é encontrada nas partes Centrais e Leste da Ilha de Java.\n" + 
					"Há também uma diversidade de café Libérico chamado Baraco, cresce nas Filipinas (nas províncias de Batangas e Cavite); " +
					"no entanto, muitos comerciantes passam o Excelsa como Baraco, pois seu suprimento é limitado e caro.\n");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Robusta");
			article.setTheme(Theme.TIPOS_GRAOS);
			article.setArticle("Seu nome científico é Coffea canephora e é o segundo maior espécie de café procurado, " +
					"depois do café Arábica. A planta é originária da área Central e Ocidental Subsaariana da África; " +
					"também crescendo no Brsil, África e Sudeste da Ásia. India, Indonésia e Vietnam, " +
					"vizinhos dos maiores fabricantes de café Robusta, os superaram, tornando-se os maiores produtores de Robusta do mundo.\n" + 
					"O grão Robusta é muito simples de manter e de produzir. Os grãos são considerados de baixa qualidade se comparado com os grãos Arábica. " +
					"São muito usados para a produção de cafés instantâneos e espressos para formarem a “crema”. Tem quase o dobro de cafeína do que os grãos Arábica.\n" + 
					"O grão robusta não foi reconhecido como uma espécia de café até o século 19, 100 anos depois do Arábica. " +
					"A planta tem um sistema de casca e cresce, aproximadamente, 10 metros. É menos sucetível à mudanças climática, doenças e pestes. " +
					"Dá uma grande colheira, embora cresça irrelugar e precise de quase 11 meses para as cerejas madurarem.\n");
			articleRepository.save(article);
			
			article = new ArticleModel();
			article.setTitle("Arábica");
			article.setTheme(Theme.TIPOS_GRAOS);
			article.setArticle("É crescida originalmente nas montanhas do Iêmen, na Península Arábica, assim como nas montanhas do sudoeste da Etiópia e do sudeste do Sudão. " +
					"Apesar de hoje as árvores serem misturadas com outros tipos de plantas.\n" + 
					"Grãos verdes Arábicas também podem ser encontrados no Mt. Marsabit, no norte do Kenya, e na Boma Plateau, no sudeste do Sudão; " +
					"Mas é incerto se essa espécie é nativa ou naturalizada.\n" + 
					"Seu arbusto cresce de 9 a 12 metros, mas sua altura pode ser regularizada para dar mais cortes. " +
					"A planta tem um sistema de ramificação aberto: suas folhas são de um verde escuro, tem forma alongada (6 a 12 cm de comprimento e 4 a 8 cm de largura) e são opostas uma da outra; " +
					"a flor é branca; as frutas são drupóides (também chamado de “fruto”), quando envelhece, sua cor muda de um vermelho vivo para roxo e normalmente tem duas sementes (também chamado de “grão de café”)\n" + 
					"Demora cerca de 7 anos para começar a render. É usualmente cultivado numa altura de 1,300 a 1,500 metros mas há espécies que se cultivam a nível do mar, em 2,800 m. " +
					"É também possível estar em baixas temperaturas sem congelas e precisam de um pouco de sombra, ao contrário da Robusta que crescem muito bem abaixo de intensa luz solar.\n");
			articleRepository.save(article);
			
		}
	}

}
