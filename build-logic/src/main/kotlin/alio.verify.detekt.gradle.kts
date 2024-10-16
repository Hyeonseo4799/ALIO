import com.skogkatt.alio.configureVerifyDetekt
import io.gitlab.arturbosch.detekt.Detekt

configureVerifyDetekt()

tasks.withType<Detekt>().configureEach {
    buildUponDefaultConfig = true // 기본 설정에서 추가 설정만 덮어쓰기
    parallel = true // 코드 분석 병렬 실행 설정
    config = files("$rootDir/config/detekt/detekt.yml")
}
