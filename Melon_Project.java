package z01_homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Melon_Project {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      // 재생 기능 실행 전, 멜론 플레이어 시스템에 노래 생성.
      // ArrayList 컬렉션 생성
      List<Song> songs = new ArrayList<Song>(); // 음악 리스트
      List<Song> excludeSongs = new ArrayList<Song>();
      PlayingPlayList playingPlayList = new PlayingPlayList();
      boolean isLoggedIn = false;
      // 객체 추가
      songs.add(new Song("Love Lee", "AKMU(악뮤)", 1));
      songs.add(new Song("Seven", "정국", 2));
      songs.add(new Song("후라이의 꿈", "AKMU(악뮤)", 3));
      songs.add(new Song("Do or Die", "임영웅", 4));
      songs.add(new Song("Elther Way", "IVE (아이브)", 5));
      songs.add(new Song("사랑은 늘 도망가", "임영웅", 6));
      songs.add(new Song("You & Me", "제니(JENNIE)", 7));
      songs.add(new Song("모래 알갱이", "임영웅", 8));

      int num = 0;
      Scanner scanner = new Scanner(System.in);
      while (true) {
         System.out.println("--------------");
         System.out.println("원하는 메뉴를 선택하세요.");
         System.out.println("1. 로그인");
         System.out.println("2. 음악 재생");
         System.out.print("3. 이전 음악 재생" + " / ");
         System.out.print("4. 다음 음악 재생" + " / ");
         System.out.print("5. 음악 재생 중지 (중지해제(재생))" + " / ");
         System.out.println("6. 셔플 재생");
         System.out.print("7. 음악 좋아요 버튼 클릭 (좋아요/좋아요 취소)"+ " / ");
         System.out.println("8. 음악 제외 버튼 클릭");
         System.out.println("9. 음악을 재생 목록에서 삭제");
         System.out.println("10. 재생목록(정보보기)");
         System.out.println("--------------");
         num = Integer.parseInt(scanner.nextLine());
         if (num == 1) { // 로그인
            isLoggedIn = true;
            System.out.println("로그인 되었습니다");
         }

         if (num == 2) { // 재생
            // 멜론 플레이어 메인 화면 top100의 1~8위 노래 리스트
            System.out.println("#재생가능한 노래 목록#");
            for (Song song : songs) { // for-each루프: 리스트 요소를 순회하면서 각 요소에 접근할때 사용
               // songs 리스트의 의 모든 song 객체를 반복하면서 각 노래를 song 변수에 할당 / 반복
               song.playableSongsInf();
            }
            System.out.println("--------------");
            // 노래 제목을 입력하면 노래가 재생됨.
            System.out.println("노래목록 중, 재생할 음악 제목을 입력하세요.");
            String title = scanner.nextLine();

            boolean songFound = false;
            for (Song song : excludeSongs) {
               if (title.equals(song.getTitle())) {
                  System.out.println("차단된 곡이여서 재생할 수 없습니다.");
                  return;
               }
            }
            for (Song song : songs) {
               if (song.getTitle().equals(title)) {
                  playingPlayList.addSongWithPlay(song, isLoggedIn);
                  songFound = true;
                  break;
               }
            }
            playingPlayList.displayPlayingPlayList();
         }

         if (num == 3) {// 이전 음악 재생
            playingPlayList.prePlaySong(isLoggedIn);
         }

         if (num == 4) {// 다음 음악 재생
            playingPlayList.nextPlaySong(isLoggedIn);
         }

         if (num == 5) {// 음악 재생 중지 (중지해제(재생))
            playingPlayList.stopPlaySong();
         }

         if (num == 6) {// 셔플 재생
            playingPlayList.randomPlaySong(isLoggedIn);
         }
         
         if (num == 7) { // 음악 좋아요 버튼 클릭 (좋아요/좋아요 취소)
            System.out.println("좋아요를 누를 노래 제목을 입력하세요");
            playingPlayList.displayPlayingPlayList();
            String title = scanner.nextLine();
            boolean songFound = false;
            for (Song song : songs) {
               if (song.getTitle().equals(title)) {
                  song.LikePlayingSong();
                  songFound = true;
                  break;
               }
            }
         }

         if (num == 8) { // 음악 제외 버튼 클릭
            System.out.println("차단할 노래 제목 입력하세요.");
            String titleToExclude = scanner.nextLine();
            boolean songFound = false;
            for (Song song : songs) {
               if (song.getTitle().equals(titleToExclude)) {
                  excludeSongs.add(song);
                  songFound = true;
                  break;
               }
            }
            if (songFound) {
               System.out.println(titleToExclude + "음악이 차단 되었습니다.");
            } else {
               System.out.println("차단된 노래가 없습니다");
            }
            System.out.println("차단목록");
            for (Song song : excludeSongs) {
               System.out.println(song.getTitle() + " - " + song.getArtist());
            }
         }
   

         if (num == 9) { // 음악을 재생 목록에서 삭제
            // 멜론 플레이어 메인 화면 top100의 1~8위 노래 리스트
            System.out.println("#재생가능한 노래 목록#");
            for (Song song : playingPlayList.getSongList()) { // for-each루프: 리스트 요소를 순회하면서 각 요소에 접근할때 사용
               // songPlayerSystemList.songs 리스트의 의 모든 song 객체를 반복하면서 각 노래를 song 변수에 할당 / 반복
               // System.out.println(song.getRanking()+ song.getTitle() + song.getArtist());
               song.playableSongsInf();
            }
            System.out.println("--------------");
            // 노래 제목을 입력하면 노래가 재생됨.
            System.out.println("노래목록 중, 삭제할 음악 제목을 입력하세요.");
            String title = scanner.nextLine();

            boolean songFound = false;
            for (Song song : songs) {
               if (song.getTitle().equals(title)) {
                  playingPlayList.deleteSong(song);
                  songFound = true;
                  break;
               }
            }
            playingPlayList.displayPlayingPlayList();
         }


         if (num == 10) { // 재생목록(정보보기)
            for (Song song : songs) {
               song.showSongInf();
            }
         }

      }
   }
}

class Song {
   private String title;
   private String artist;
   private int ranking;
   private int steamingCount;
   private int like;
   private int pos;

   public Song(String title, String artist, int ranking) {
      this.title = title;
      this.artist = artist;
      this.ranking = ranking;
      this.steamingCount = 0;
      this.like = 0;
   }

   public String getTitle() {
      return title;
   }

   public String getArtist() {
      return artist;
   }

   public int getRanking() {
      return ranking;
   }

   public int getSteamingCount() {
      return steamingCount;
   }

   public int getLike() {
      return like;
   }

   public void playableSongsInf() {
      System.out.println(getRanking() + "." + " " + getTitle() + " - " + getArtist());
   }

   public void SetStreamingCount(int steamingCount) {
      this.steamingCount = steamingCount;
   }

//   public void playSong(PlayingPlayList playingPlayList) {
//      System.out.println("노래를 재생합니다.");
//      System.out.println("재생 중: " + this.getTitle() 
//      + " - " + this.getArtist());
//      steamingCount++;
//      playingPlayList.addSongToPlayingPlayList(this);
//      int lastPos = playingPlayList.getSongList().size() - 1;
//      playingPlayList.setPos(lastPos);
//   }

   public void LikePlayingSong() {
      like++;
      if (like % 2 == 0) {
         like -= 2;
         System.out.println(title + "의 좋아요 취소되었습니다.");
      } else if (like % 1 == 0) {
         System.out.println(title + "의 좋아요가 반영되었습니다.");
      }
   }

   public void showSongInf() {
      System.out.println(getTitle() + " - " + getArtist() + " " + "순위:" + getRanking() + " / " + "스트리밍 수"
            + getSteamingCount() + " / " + "좋아요 수" + getLike());
   }

}

class PlayingPlayList {
   private List<Song> songList;// 클래스 필드로 선언하면 해당 필드는 해당 클래스의 객체에 속함
   // 따라서 객체 간 데이터 공유와 유지 가능
   private int pos; // 현재 재생중인 노래의 인덱스를 나타내는 변수, 값은 현재 재생 중인 노래의 songList에서 인덱스를 가리킴.

   public PlayingPlayList() {
      this.songList = new ArrayList<>();
      //pos = 0;
   }

   public List<Song> getSongList() {
      return this.songList;
   }

   public void setPos(int pos) {
      this.pos = pos;
   }

   public void addSongToPlayingPlayList(Song song) {
      songList.add(song);
   }

   public void displayPlayingPlayList() {
      // 저장된 총 객체 수 얻기 (size)
      int size = songList.size();

      System.out.print("#재생목록# :");
      System.out.println(size + "곡");
      for (Song song : songList) {
         System.out.println(song.getTitle() + " - " + song.getArtist());
      }
   }

   public void deleteSong(Song song) {
      int index = songList.indexOf(song);
      this.pos = index - 1;
      if (index < 0) {
         this.pos = 0;
      }
      songList.remove(song);

   }

   public void addSongWithPlay(Song song, boolean isLoggedIn) {
      if (!checkLoggedIn(isLoggedIn)) {
         System.out.println("1분 미리 듣기만 가능합니다.");
      }
      System.out.println("1곡을 재생목록에 담았습니다.");
      System.out.println("노래를 재생합니다.");
      System.out.println("재생 중: " + song.getTitle()
      + " - " + song.getArtist());
      song.SetStreamingCount(song.getSteamingCount() + 1);
      this.addSongToPlayingPlayList(song);
      int lastPos = this.getSongList().size() - 1;
      this.setPos(lastPos);
   }

   public boolean checkLoggedIn(boolean isLoggedIn) {
      if (!isLoggedIn) {
         System.out.print("//(비회원 팝업창)로그인을 해주세요.");
      }
      return isLoggedIn;
   }

   
   public void prePlaySong(boolean isLoggedIn) {
      if (!checkLoggedIn(isLoggedIn)) {
         System.out.println("1분 미리 듣기만 가능합니다.");
      }
      if (pos >= 0) { // 이전 노래로 이동
         pos--;
         if (pos >= 0 && pos < songList.size()) {
            Song previousSong = songList.get(pos);
            System.out.println("이전 노래를 재생합니다.");
            System.out.println("재생 중: " + previousSong.getTitle() +
                  " - " + previousSong.getArtist());
         } else {
            System.out.println("재생할 이전 노래가 없습니다");
         }
      } else {

         System.out.println("현재 재생 중인 노래가 없습니다.");
      }
   }

   public void nextPlaySong(boolean isLoggedIn) {
      if (!checkLoggedIn(isLoggedIn)) {
         System.out.println("1분 미리 듣기만 가능합니다.");
      }

      if (pos <= this.songList.size() - 1) { // 다음 노래로 이동
         pos++;
         if (pos >= 0 && pos < songList.size()) {
            Song nextSong = songList.get(pos);
            System.out.println("다음 노래를 재생합니다.");
            System.out.println("재생 중: " + nextSong.getTitle() +
                  " - " + nextSong.getArtist());
         } else {
            System.out.println("재생할 다음 노래가 없습니다");
         }
      } else {

         System.out.println("현재 재생 중인 노래가 없습니다.");
      }
   }

   public void stopPlaySong() {
      System.out.println("노래재생을 일시정지 합니다.");
      Song stopPlaySong = songList.get(this.pos);
      System.out.println("일시정지 중: " + stopPlaySong.getTitle() + " - " + stopPlaySong.getArtist());
   }

   public void randomPlaySong(boolean isLoggedIn) {
      if (!checkLoggedIn(isLoggedIn)) {
         System.out.println("1분 미리 듣기만 가능합니다.");
      }
      int randomPos = (int) (Math.random() * songList.size());
      System.out.println("셔플 재생을 합니다.");
      Song stopPlaySong = songList.get(randomPos);
      System.out.println("재생 중: : " + stopPlaySong.getTitle() + " - " + stopPlaySong.getArtist());
   }
}