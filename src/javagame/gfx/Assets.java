package javagame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage bowserDn, bowserLt, bowserRt, bowserUp, grass, dirt, rock, bigRock1, bigRock2, mediumRock1, mediumRock2, smallRock1, smallRock2, bigBlock;
	public static BufferedImage[] bowserDN, bowserLT, bowserRT, bowserUP;
	public static BufferedImage[] goombaID;
	
	public static void init(){
		SpriteSheet bowserSheet = new SpriteSheet(ImageLoader.loadImage("/textures/BowserSpriteSheet.png"));
		
		bowserDN = new BufferedImage[4];
		bowserLT = new BufferedImage[4];
		bowserRT = new BufferedImage[4];
		bowserUP = new BufferedImage[4];
		
		bowserDN[0] = bowserSheet.crop(6, 149, 59, 62);
		bowserDN[1] = bowserSheet.crop(351, 150, 60, 65);
		bowserDN[2] = bowserSheet.crop(6, 149, 59, 62);
		bowserDN[3] = bowserSheet.crop(896, 152, 59, 65);
		bowserLT[0] = bowserSheet.crop(1155, 73, 68, 62);
		bowserLT[1] = bowserSheet.crop(840, 74, 66, 59);
		bowserLT[2] = bowserSheet.crop(1155, 73, 68, 62);
		bowserLT[3] = bowserSheet.crop(234, 75, 66, 60);
		bowserRT[0] = bowserSheet.crop(1253, 9, 67, 62);
		bowserRT[1] = bowserSheet.crop(842, 8, 66, 60);
		bowserRT[2] = bowserSheet.crop(1253, 9, 67, 62);
		bowserRT[3] = bowserSheet.crop(236, 4, 66, 61);
		bowserUP[0] = bowserSheet.crop(5, 530, 58, 68);
		bowserUP[1] = bowserSheet.crop(767, 532, 57, 68);
		bowserUP[2] = bowserSheet.crop(5, 530, 58, 68);
		bowserUP[3] = bowserSheet.crop(214, 531, 58, 67);
		
		SpriteSheet goombaSheet = new SpriteSheet(ImageLoader.loadImage("/textures/PiTGoombaSheet.png"));
		
		goombaID = new BufferedImage[4];
		
		goombaID[0] = goombaSheet.crop(13, 41, 19, 22);
		goombaID[1] = goombaSheet.crop(64, 41, 19, 22);
		goombaID[2] = goombaSheet.crop(13, 41, 19, 22);
		goombaID[3] = goombaSheet.crop(166, 41, 19, 22);
		
		SpriteSheet bigRockSheet = new SpriteSheet(ImageLoader.loadImage("/textures/BigRocks.png"));
		SpriteSheet mediumRockSheet = new SpriteSheet(ImageLoader.loadImage("/textures/MediumRocks.png"));
		SpriteSheet smallRockSheet = new SpriteSheet(ImageLoader.loadImage("/textures/SmallRocks.png"));
		SpriteSheet propsSheet = new SpriteSheet(ImageLoader.loadImage("/textures/BowserProps.png"));
		grass = ImageLoader.loadImage("/textures/grass_tile.jpg");
		dirt = ImageLoader.loadImage("/textures/dirt_tile.jpg");
		rock = ImageLoader.loadImage("/textures/rock_tile.jpg");
		bowserDn = bowserSheet.crop(6, 149, 59, 62);
		bowserLt = bowserSheet.crop(1156, 73, 67, 62);
		bowserRt = bowserSheet.crop(1155, 8, 67, 62);
		bowserUp = bowserSheet.crop(5, 530, 57, 68);
		//goomba = goombaSheet.crop(13, 41, 19, 22);
		bigRock1 = bigRockSheet.crop(222, 230, 215, 215);
		bigRock2 = bigRockSheet.crop(217, 452, 222, 207);
		mediumRock1 = mediumRockSheet.crop(8, 0, 115, 116);
		mediumRock2 = mediumRockSheet.crop(6, 237, 115, 119);
		smallRock1 = smallRockSheet.crop(1, 59, 98, 55);
		smallRock2 = smallRockSheet.crop(1, 1, 81, 57);
		bigBlock = propsSheet.crop(85, 287, 64, 83);
		
		
	}
}
